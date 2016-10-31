//import com.tickets.controller.TicketController;
//import com.tickets.validator.NewTicketValidator;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
///**
// * Created by lukasz.homik on 2016-10-11.
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration
////@WebAppConfiguration
//public class TicketControllerTest {
//
//    @Autowired WebApplicationContext context;
//    @Autowired MockHttpSession session;
//    @Autowired MockHttpServletRequest request;
//    @Autowired TicketController ticketController;
//
//    @Mock
//    NewTicketValidator ticketFormValidator;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
////        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        ticketController = new TicketController();
//        this.mockMvc = standaloneSetup(ticketController).build();
//        when(ticketFormValidator.supports(any(Class.class))).thenReturn(true);
////        mockMvc = MockMvcBuilders
////                .webAppContextSetup(context)
////                .apply(new WebSecurityConfig())
////                .build();
//    }
//
//    @Test
//    public void testHomePage() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(view().name("redirect:/home"));
////        this.mockMvc.perform(get("/home").session(session)
////                .accept(MediaType.TEXT_HTML))
////                .andExpect(status().isOk())
////                .andExpect(view().name("home"));
//    }
//
//    @Test
//    public void testNewTicket() throws Exception {
//        mockMvc.perform(get("/home/newTicket"))
//                .andExpect(view().name("TicketForm"));
//    }
//
//}
