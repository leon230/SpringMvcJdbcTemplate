package com.tickets.test;

import com.tickets.controller.TicketController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by lukasz.homik on 2016-10-11.
 */
//22
public class TicketControllerTest {

    @Autowired WebApplicationContext wac;
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    @Autowired TicketController tc;

    private MockMvc mockMvc;

    @Before
    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        tc = new TicketController();
        this.mockMvc = standaloneSetup(tc).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("redirect:/home"));
//        this.mockMvc.perform(get("/home").session(session)
//                .accept(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(view().name("home"));
    }
    @Test
    public void testNewTicket() throws Exception {
        mockMvc.perform(get("/home/newTicket"))
                .andExpect(view().name("TicketForm"));
    }

}
