import com.tickets.model.Ticket;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lukasz.homik on 2016-09-08.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = MvcConfiguration.class)
public class TicketTest {

//    @Autowired
//    private ApplicationContext applicationContext;

    @Autowired
    private Ticket ticket;

//    @Test
//    public void cdShouldNotBeNull() {
//        assertNotNull(ticket);
//    }

    @Test
    public void testTicketCreate(){
        Ticket ticket = new Ticket();
        ticket.setAccOwner("AccOwner");
        Assert.assertNotNull(ticket.getAccOwner());
        Assert.assertEquals("AccOwner", ticket.getAccOwner());
    }

}
