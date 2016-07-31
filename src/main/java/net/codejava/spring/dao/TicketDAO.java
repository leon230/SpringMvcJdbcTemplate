package net.codejava.spring.dao;

import java.util.List;
import net.codejava.spring.model.Ticket;

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
