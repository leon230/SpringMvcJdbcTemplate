package com.tickets.dao;

import java.util.List;

import com.tickets.model.Ticket;

/**
 * Defines DAO operations for the contact model.
 *
 */
public interface TicketDAO {
	
	public void saveOrUpdate(Ticket ticket);
	
	public void delete(int ticketId);
	
	public Ticket get(int ticketId);
	
	public List<Ticket> list();
}
