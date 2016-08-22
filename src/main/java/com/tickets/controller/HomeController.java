package com.tickets.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.tickets.dao.ChartsDAO;
import com.tickets.dao.TicketDAO;
import com.tickets.dao.UserDAO;
import com.tickets.model.ChartKeyValue;
import com.tickets.model.Ticket;
import com.tickets.model.User;
import com.tickets.validator.NewTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class HomeController {

	//private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private ChartsDAO chartsDAO;
	@Autowired
	private UserDAO userDAO;
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
		List<Ticket> listTicket = ticketDAO.list();
		model.addObject("listTicket", listTicket);
		model.addObject("title", "Tickets list");
		model.addObject("title2", "is checked");
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value="/home/users")
    public ModelAndView listuser(ModelAndView model) throws IOException{
        List<User> listuser = userDAO.listuser("luki");
        model.addObject("listuser", listuser);
        model.setViewName("userLogin");

        return model;
    }
	
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
		initModelList(model);
		return model;
	}

    @RequestMapping(value = "/home/saveTicket", method = RequestMethod.POST)
    public ModelAndView CheckForm(@ModelAttribute ("TicketForm") @Validated Ticket ticket, BindingResult result
			, ModelAndView model) {
        if (result.hasErrors()) {
			initModelList(model);
			//model.setViewName("TicketForm");
			return new ModelAndView("TicketForm");
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
		initModelList(model);
		model.addObject("TicketForm", ticket);
		
		return model;
	}

	//@ModelAttribute("clusters")
	private void initModelList(ModelAndView model) {
		List<String> clusterList = new ArrayList<String>();
		clusterList.add("Billing");
		clusterList.add("Reporting");
		clusterList.add("OPS");
		clusterList.add("UTMS");
		clusterList.add("SAP");
		clusterList.add("Other");
		model.addObject("clusters", clusterList);

		List<String> statuses = new ArrayList<String>();
		statuses.add("In queue");
		statuses.add("In progress");
		statuses.add("Sent for testing");
		statuses.add("Closed");
		model.addObject("statuses", statuses);

		List<String> priorities = new ArrayList<String>();
		priorities.add("High");
		priorities.add("Medium");
		priorities.add("Low");
		model.addObject("priorities", priorities);
	}
/**
 * Security mapping
 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

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
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

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
