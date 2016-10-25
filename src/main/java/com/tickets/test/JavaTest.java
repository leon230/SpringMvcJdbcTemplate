package com.tickets.test;
import com.tickets.model.Ticket;
import junit.framework.Assert;
/**
 * Created by Lukasz.Homik on 2016-10-25.
 */
public class JavaTest {

    @org.junit.Test
    public void testTicketCreate(){
        Ticket ticket = new Ticket();
        ticket.setAccOwner("AccOwner");
        Assert.assertNotNull(ticket.getAccOwner());
        Assert.assertEquals("AccOwner", ticket.getAccOwner());
//2
    }

}
