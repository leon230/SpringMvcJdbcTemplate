package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Contact;
import net.codejava.spring.model.Ticket;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface ContactDAO {
	
	public void saveOrUpdate(Ticket ticket);
	
	public void delete(int contactId);
	
	public Ticket get(int ticketId);
	
	public List<Ticket> list();
}
