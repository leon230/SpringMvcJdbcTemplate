package com.tickets.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tickets.dao.ChartsDAO;
import com.tickets.dao.TicketDAO;
import com.tickets.model.ChartKeyValue;
import com.tickets.model.Filter;
import com.tickets.model.Ticket;
import com.tickets.utils.SaveToFile;
import com.tickets.validator.NewTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class TicketController {

	//private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private ChartsDAO chartsDAO;
//	@Autowired
//	private UserDAO userDAO;
	@Autowired
	NewTicketValidator ticketFormValidator;

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(userFormValidator);
//	}
	@InitBinder("TicketForm")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(ticketFormValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value="/")
	public String redirect(){
		return "redirect:/home";
	}

	@RequestMapping(value="/home")
	public ModelAndView listTicket(ModelAndView model) throws IOException{
		List<Ticket> listTicket = ticketDAO.list(null);
//		DataFilter df = new DataFilter(true);
//		model.addObject("isclosed", df.getIsClosed());
		Filter filter = new Filter();
		filter.setCondition();
		model.addObject("condition",filter.getCondition());
        //filter.setCourses(Ticket.getClustersList());
		model.addObject("filter",filter);
		model.addObject("listTicket", listTicket);
		model.addObject("title", "Tickets list");
		model.setViewName("home");

		return model;
	}

//	@RequestMapping(value="/home/users")
//    public ModelAndView listuser(ModelAndView model) throws IOException{
//        List<User> listuser = userDAO.listuser("luki");
//        model.addObject("listuser", listuser);
//        model.setViewName("userLogin");
//
//        return model;
//    }
	
	@RequestMapping(value = "/home/newTicket", method = RequestMethod.GET)
	public ModelAndView newTicket(ModelAndView model) {
		Ticket newTicket = new Ticket();
		SimpleDateFormat printFormat = new SimpleDateFormat("yyyyMMdd_kkmmss");
		Date date = new Date();
		newTicket.setNumber(System.getProperty("user.name") + "_" + printFormat.format(date));
		newTicket.setReportedBy(System.getProperty("user.name"));
		newTicket.setTstatus("In queue");
		model.addObject("TicketForm", newTicket);
		model.setViewName("TicketForm");
		model.addObject("clusters", Ticket.getClustersList());
		model.addObject("statuses", Ticket.getStatusesList());
		model.addObject("priorities", Ticket.getPrioritiesList());
		return model;
	}

    @RequestMapping(value = "/home/saveTicket", method = RequestMethod.POST)
    public ModelAndView CheckForm(@ModelAttribute ("TicketForm") @Validated Ticket ticket, BindingResult result
			, ModelAndView model) {
        if (result.hasErrors()) {
        	ticket.initModelList();
			model.setViewName("TicketForm");
			model.addObject("clusters", Ticket.getClustersList());
			model.addObject("statuses", Ticket.getStatusesList());
			model.addObject("priorities", Ticket.getPrioritiesList());
			return model;
        }
        else {
				ticketDAO.saveOrUpdate(ticket);
			return new ModelAndView("redirect:/");
        }
		//return "redirect:/";
    }
	
	@RequestMapping(value = "/home/deleteTicket", method = RequestMethod.GET)
	public ModelAndView deleteTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		ticketDAO.delete(ticketId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/home/editTicket", method = RequestMethod.GET)
	public ModelAndView editTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		Ticket ticket = ticketDAO.get(ticketId);
		ModelAndView model = new ModelAndView("TicketForm");
		model.addObject("clusters", Ticket.getClustersList());
		model.addObject("statuses", Ticket.getStatusesList());
		model.addObject("priorities", Ticket.getPrioritiesList());
		model.addObject("TicketForm", ticket);
		
		return model;
	}


/**
 * Charts Data mappring
 */
	@RequestMapping(value = "/charts**", method = RequestMethod.GET)
	public ModelAndView chartsPage() {
		List<ChartKeyValue> progressData = chartsDAO.getProgressData();
		List<ChartKeyValue> priorityData = chartsDAO.getPriorityData();
		List<ChartKeyValue> prioritySolveData = chartsDAO.getPrioritySolveData();

		ModelAndView model = new ModelAndView();
		model.addObject("progressDataList", progressData);
		model.addObject("priorityDataList", priorityData);
		model.addObject("prioritySolveDataList", prioritySolveData);
		model.addObject("title", "Basic KPI");
		model.setViewName("charts/BasicKpi");

		return model;
	}


	@RequestMapping(value = "/filter**", method = RequestMethod.GET)
	public String initForm(Model model) {
		Filter filter = new Filter();
		model.addAttribute("filter", filter);
		List<String> clusters = new ArrayList<String>();
		List<String> priorities = new ArrayList<String>();
		List<String> statuses = new ArrayList<String>();
		clusters.addAll(Ticket.getClustersList());
		priorities.addAll(Ticket.getPrioritiesList());
		statuses.addAll(Ticket.getStatusesList());
		model.addAttribute("clusters", clusters);
		model.addAttribute("priorities", priorities);
		model.addAttribute("statuses", statuses);
		return "filter/TicketFilter";
	}

	@RequestMapping(value = "/ApplyFilter", method = RequestMethod.POST)
	public ModelAndView CheckForm(@ModelAttribute("filter") Filter filter, BindingResult result
			, ModelAndView model) {
		model.addObject("filter", filter);
		return new ModelAndView("redirect:/");
	}
/*
	Export to file
 */
	@RequestMapping(value = "/home/export", method = RequestMethod.GET)
	public String getFile(HttpServletRequest request,
						  HttpServletResponse response) throws IOException{
		int BUFFER_SIZE = 4096;
//		ServletContext context = request.getServletContext();
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filePath = "/Export.csv";
		String fullPath = appPath + filePath;
		List<Ticket> listTicket = ticketDAO.list(null);

		SaveToFile sv = new SaveToFile(listTicket,fullPath);
		sv.saveFile();

		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

		return "redirect:/home";
	}

/**
 * TO DO
 */
//		@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
//	@InitBinder
//	public void binder(WebDataBinder binder) {
//		binder.registerCustomEditor(Date.class, new DateEditor());
//	}
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(sun.security.krb5.internal.Ticket);
//	}



//	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
//	public ModelAndView saveTicket(@ModelAttribute Ticket ticket) {
//		ticketDAO.saveOrUpdate(ticket);
//		return new ModelAndView("redirect:/");
//	}
}
