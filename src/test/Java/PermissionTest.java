//package com.tickets.test;
//
//import com.tickets.controller.TicketController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
///**
// * Created by lukasz.homik on 2016-10-11.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration
//public class PermissionTest {
//    @Autowired
//    TicketController ticketController;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
////        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        ticketController = new TicketController();
//        this.mockMvc = standaloneSetup(ticketController).build();
//
////        mockMvc = MockMvcBuilders
////                .webAppContextSetup(context)
////                .apply(new WebSecurityConfig())
////                .build();
//    }
//
//
//
//    @Test
//    public void testNewTicket() throws Exception {
//        mockMvc.perform(get("/home/newTicket"))
//                .andExpect(view().name("TicketForm"));
//    }
//
//}
