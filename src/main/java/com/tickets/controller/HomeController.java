package com.tickets.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.tickets.dao.TicketDAO;
import com.tickets.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods.
 *
 */
@Controller
public class HomeController {

	@Autowired
	private TicketDAO ticketDAO;
	
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
		model.addObject("ticket", newTicket);
		model.setViewName("TicketForm");
		return model;
	}


	
//	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
//	public ModelAndView saveTicket(@ModelAttribute Ticket ticket) {
//		ticketDAO.saveOrUpdate(ticket);
//		return new ModelAndView("redirect:/");
//	}
    @RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
    public String CheckForm(@Valid Ticket ticket, BindingResult result) {
        if (result.hasErrors()) {
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
