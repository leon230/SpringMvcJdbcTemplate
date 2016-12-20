import com.tickets.dao.TicketDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * Created by lukasz.homik on 2016-12-20.
 */
public class MockitoTest {

    @Mock
    TicketDAOImpl ticketDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1(){
        assertNotNull(ticketDAO);
    }


}
