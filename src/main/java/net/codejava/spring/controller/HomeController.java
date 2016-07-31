package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.model.Contact;

import net.codejava.spring.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
		List<Ticket> listContact = contactDAO.list();
		model.addObject("listTicket", listContact);
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
	
	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
	public ModelAndView saveTicket(@ModelAttribute Ticket ticket) {
		contactDAO.saveOrUpdate(ticket);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editTicket", method = RequestMethod.GET)
	public ModelAndView editTicket(HttpServletRequest request) {
		int ticketId = Integer.parseInt(request.getParameter("id"));
		Ticket ticket = contactDAO.get(ticketId);
		ModelAndView model = new ModelAndView("TicketForm");
		model.addObject("ticket", ticket);
		
		return model;
	}
}
