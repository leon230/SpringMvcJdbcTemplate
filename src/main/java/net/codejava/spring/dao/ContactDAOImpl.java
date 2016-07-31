package net.codejava.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.Contact;

import net.codejava.spring.model.Ticket;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the ContactDAO interface.
 *
 */
public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Ticket ticket) {
		if (ticket.getId() > 0) {
			// update
			String sql = "UPDATE tickets SET TICKET_NO=?, TICKET_TITLE=?, TICKET_OWNER=?, "
						+ "CLUSTER=? WHERE ID=?";
			jdbcTemplate.update(sql, ticket.getNumber(), ticket.getTitle(),
					ticket.getOwner(), ticket.getCluster(), ticket.getId());
		} else {
			// insert
			String sql = "INSERT INTO tickets (TICKET_NO,TICKET_TITLE,TICKET_OWNER,CLUSTER)"
						+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, ticket.getNumber(), ticket.getTitle(),
					ticket.getOwner(), ticket.getCluster());
		}
		
	}

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM tickets WHERE ID=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Ticket> list() {
		String sql = "SELECT ID,TICKET_NO,TICKET_TITLE,CLUSTER,TICKET_OWNER FROM tickets";
		List<Ticket> listTicket = jdbcTemplate.query(sql, new RowMapper<Ticket>() {

			@Override
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket aTicket = new Ticket();
	
				aTicket.setId(rs.getInt("ID"));
				aTicket.setNumber(rs.getString("TICKET_NO"));
				aTicket.setTitle(rs.getString("TICKET_TITLE"));
				aTicket.setCluster(rs.getString("CLUSTER"));
				aTicket.setOwner(rs.getString("TICKET_OWNER"));
				
				return aTicket;
			}
			
		});
		
		return listTicket;
	}

	@Override
	public Ticket get(int ticketId) {
		String sql = "SELECT * FROM tickets WHERE ID=" + ticketId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Ticket>() {

			@Override
			public Ticket extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Ticket ticket = new Ticket();
					ticket.setId(rs.getInt("ID"));
					ticket.setNumber(rs.getString("TICKET_NO"));
					ticket.setTitle(rs.getString("TICKET_TITLE"));
					ticket.setOwner(rs.getString("TICKET_OWNER"));
					ticket.setCluster(rs.getString("CLUSTER"));
					return ticket;
				}
				
				return null;
			}
			
		});
	}

}
