package com.tickets.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.tickets.dao.TicketDAO;
import com.tickets.model.Ticket;
import com.tickets.utils.DateEditor;
import com.tickets.validator.NewTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods.
 *
 */
@Controller
public class HomeController {


	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	NewTicketValidator userFormValidator;

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(userFormValidator);
//	}
	@InitBinder("ticket")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(userFormValidator);
	}

	@RequestMapping(value="/")
	public ModelAndView listTicket(ModelAndView model) throws IOException{
		List<Ticket> listTicket = ticketDAO.list();
		model.addObject("listTicket", listTicket);
		model.setViewName("home");
		
		return model;
	}
	
	@RequestMapping(value = "/newTicket", method = RequestMethod.GET)
	public ModelAndView newTicket(ModelAndView model) {
		Ticket newTicket = new Ticket();
		SimpleDateFormat printFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		newTicket.setNumber(System.getProperty("user.name") + "_" + printFormat.format(date));
		model.addObject("ticket", newTicket);
		model.setViewName("TicketForm");
		return model;
	}
//	@InitBinder
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
    @RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
    public String CheckForm(@ModelAttribute ("ticket") @Valid Ticket ticket, BindingResult result
			, ModelAndView model) {
        if (result.hasErrors()) {
			model.setViewName("TicketForm");
            return "TicketForm";
        }
        else {
			ticketDAO.saveOrUpdate(ticket);
            return "redirect:/";
        }
		//return "redirect:/";
    }
	
	@RequestMapping(value = "/deleteTicket", method = RequestMethod.GET)
	public ModelAndView deleteTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		ticketDAO.delete(ticketId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editTicket", method = RequestMethod.GET)
	public ModelAndView editTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		Ticket ticket = ticketDAO.get(ticketId);
		ModelAndView model = new ModelAndView("TicketForm");
		model.addObject("ticket", ticket);
		
		return model;
	}
}
