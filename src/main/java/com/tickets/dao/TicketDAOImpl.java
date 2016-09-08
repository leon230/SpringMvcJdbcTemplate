package com.tickets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.tickets.model.Ticket;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the TicketDAO interface.
 *
 */
public class TicketDAOImpl implements TicketDAO {

	private JdbcTemplate jdbcTemplate;
	
	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Ticket ticket) {
		if (ticket.getId() > 0) {
			// update
			String sql = "UPDATE tickets SET TICKET_NO=?, TICKET_TITLE=?, TICKET_OWNER=?, "
						+ "CLUSTER=?, OPEN_DATE=?, CLOSE_DATE=?, DESCRIPTION=?" +
					",REPORTED_BY=?, PRIORITY=?, STATUS=?, ACC_OWNER=? WHERE ID=?";
			jdbcTemplate.update(sql, ticket.getNumber(), ticket.getTitle(),
					ticket.getOwner(), ticket.getCluster(), ticket.getOpenDate(),ticket.getCloseDate(),
					ticket.getDescription(), ticket.getReportedBy(), ticket.getPriority(), ticket.getTstatus(),
					ticket.getAccOwner(), ticket.getId());
		} else {
			// insert
			String sql = "INSERT INTO tickets (TICKET_NO,TICKET_TITLE,TICKET_OWNER,CLUSTER, OPEN_DATE," +
					"CLOSE_DATE,DESCRIPTION,REPORTED_BY,PRIORITY,STATUS,ACC_OWNER)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, ticket.getNumber(), ticket.getTitle(),
					ticket.getOwner(), ticket.getCluster(), ticket.getOpenDate(),ticket.getCloseDate(),
					ticket.getDescription(), ticket.getReportedBy(), ticket.getPriority(), ticket.getTstatus(),
					ticket.getAccOwner());
		}
	}

	@Override
	public void delete(int ticketId) {
		String sql = "DELETE FROM tickets WHERE ID=?";
		jdbcTemplate.update(sql, ticketId);
	}

	@Override
	public List<Ticket> list(String condition) {
		if (condition == null){
			condition = "1=1";
		}
		String sql = "SELECT * FROM tickets WHERE " + condition + " ORDER BY ID DESC";
		List<Ticket> listTicket = jdbcTemplate.query(sql, new RowMapper<Ticket>() {

			@Override
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket aTicket = new Ticket();
	
				aTicket.setId(rs.getInt("ID"));
				aTicket.setNumber(rs.getString("TICKET_NO"));
				aTicket.setTitle(rs.getString("TICKET_TITLE"));
				aTicket.setCluster(rs.getString("CLUSTER"));
				aTicket.setOwner(rs.getString("TICKET_OWNER"));
				aTicket.setOpenDate(rs.getDate("OPEN_DATE"));
				aTicket.setCloseDate(rs.getDate("CLOSE_DATE"));
				aTicket.setDescription(rs.getString("DESCRIPTION"));
				aTicket.setReportedBy(rs.getString("REPORTED_BY"));
				aTicket.setPriority(rs.getString("PRIORITY"));
				aTicket.setTstatus(rs.getString("STATUS"));
				aTicket.setAccOwner(rs.getString("ACC_OWNER"));

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
					ticket.setOpenDate(rs.getDate("OPEN_DATE"));
					ticket.setCloseDate(rs.getDate("CLOSE_DATE"));
					ticket.setDescription(rs.getString("DESCRIPTION"));
					ticket.setReportedBy(rs.getString("REPORTED_BY"));
					ticket.setPriority(rs.getString("PRIORITY"));
					ticket.setTstatus(rs.getString("STATUS"));
					ticket.setAccOwner(rs.getString("ACC_OWNER"));
					return ticket;
				}
				
				return null;
			}
			
		});
	}

}
