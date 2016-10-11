package com.tickets.test;

import com.tickets.controller.TicketController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by lukasz.homik on 2016-10-11.
 */
public class TicketControllerTest {
    @Test
    public void testTicketHomePage() throws Exception {
        TicketController tc = new TicketController();
        MockMvc mockMvc = standaloneSetup(tc).build();

//        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
}
