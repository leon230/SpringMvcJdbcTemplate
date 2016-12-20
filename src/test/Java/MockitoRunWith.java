import com.tickets.dao.TicketDAOImpl;
import com.tickets.dao.UserDAO;
import com.tickets.model.Ticket;
import com.tickets.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by lukasz.homik on 2016-12-20.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoRunWith {
    @Mock
    TicketDAOImpl ticketDAO;
    @Mock
    UserDAO userDAO;
    @Mock
    Ticket ticket;


    @Test
    public void test1(){
        assertNotNull(ticketDAO);
    }

    @Test
    public void username_is_not_null(){
        User user = new User(1,"Luki","l123","ROLE_ADMIN");
        when(userDAO.getUser(anyString())).thenReturn(user);
        assertNotNull(userDAO.getUser("Luki"));

    }
    @Test
    public void InitTicketLists(){
        ticket = spy(new Ticket());
        ticket.initModelList();
//        verify(ticket, times(1)).initModel2();
    }



}
