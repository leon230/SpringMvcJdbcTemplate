package com.tickets.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
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
import com.tickets.utils.DownloadFile;
import com.tickets.utils.SaveToFile;
import com.tickets.validator.NewTicketValidator;
import com.tickets.validator.TicketFilterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Import(com.tickets.validator.TicketFilterValidator.class)
public class TicketController {

	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private ChartsDAO chartsDAO;
	@Autowired
	private NewTicketValidator ticketFormValidator;
	@Autowired
	private TicketFilterValidator ticketFilterValidator;
	@Autowired
	private Filter ticketFilter;

	private Calendar calendar = Calendar.getInstance();
	private List<Ticket> listTicket;

	@InitBinder("TicketForm")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(ticketFormValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@InitBinder
		public void homeBinder(){
		listTicket = ticketDAO.list(ticketFilter.getCondition());
	}

/**
 * Home mapping
 */
	@RequestMapping(value="/")
	public String redirect(){
		return "redirect:/home";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView listTicket(ModelAndView model) throws IOException{

		model.addObject("currentDate",calendar.getTime());
		model.addObject("filter",ticketFilter);
		model.addObject("listTicket", listTicket);
		model.addObject("title", "Tickets list");
		model.setViewName("home");

		return model;
	}

/**
 * Ticket actions New,Edit,Delete,Save
 */
	@RequestMapping(value = "**/newTicket", method = RequestMethod.GET)
	public ModelAndView newTicket(ModelAndView model) {
		Ticket newTicket = new Ticket(System.getProperty("user.name"),System.getProperty("user.name"),"In queue");

		model.addObject("TicketForm", newTicket);
		model.setViewName("TicketForm");
		model.addObject("clusters", Ticket.getClustersList());
		model.addObject("statuses", Ticket.getStatusesList());
		model.addObject("priorities", Ticket.getPrioritiesList());
		return model;
	}

    @RequestMapping(value = "**/saveTicket", method = RequestMethod.POST)
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

    }
	
	@RequestMapping(value = "**/deleteTicket", method = RequestMethod.GET)
	public ModelAndView deleteTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		ticketDAO.delete(ticketId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "**/editTicket", method = RequestMethod.GET)
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

/**
 * Ticket main table filter mapping and apply
 */
	@RequestMapping(value = "/filter**", method = RequestMethod.GET)
	public ModelAndView newFilter(ModelAndView model) {
		List<String> clusters = new ArrayList<String>();
		List<String> priorities = new ArrayList<String>();
		List<String> statuses = new ArrayList<String>();
		clusters.addAll(Ticket.getClustersList());
		priorities.addAll(Ticket.getPrioritiesList());
		statuses.addAll(Ticket.getStatusesList());
		model.addObject("filter", ticketFilter);
		model.setViewName("filter/TicketFilter");
		model.addObject("clusters", clusters);
		model.addObject("priorities", priorities);
		model.addObject("statuses", statuses);
		return model;
	}

	@RequestMapping(value = "/ApplyFilter", method = RequestMethod.POST)
	public ModelAndView CheckForm(@ModelAttribute("filter") Filter filter, BindingResult result
			, ModelAndView model) {
		TicketFilterValidator filterValidator = new TicketFilterValidator();
		filterValidator.validate(filter, result);

		if (result.hasErrors()){
			List<String> clusters = new ArrayList<String>();
			List<String> priorities = new ArrayList<String>();
			List<String> statuses = new ArrayList<String>();
			clusters.addAll(Ticket.getClustersList());
			priorities.addAll(Ticket.getPrioritiesList());
			statuses.addAll(Ticket.getStatusesList());

			model.setViewName("filter/TicketFilter");
			model.addObject("filter", filter);
			model.addObject("clusters", clusters);
			model.addObject("priorities", priorities);
			model.addObject("statuses", statuses);
			filter.setCondition();
			return model;
		}
		else {
			filter.setCondition();
			model.addObject("filter", filter);
			return new ModelAndView("redirect:/");
		}
	}
/**
	Export to excel file
 */
	@RequestMapping(value = "**/export", method = RequestMethod.GET)
	public String getFile(HttpServletRequest request,
						  HttpServletResponse response) throws IOException{
		DownloadFile.downloadCSV(request,response,listTicket);

		return "redirect:/home";
	}
}
