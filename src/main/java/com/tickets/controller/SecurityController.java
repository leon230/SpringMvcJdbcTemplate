package com.tickets.controller;

import com.tickets.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ResourceBundle;

/**
 * Created by lukasz.homik on 2016-09-05.
 */
@Controller
public class SecurityController {

    @Autowired
    FilterService filterService;
    private ResourceBundle bunlde = ResourceBundle.getBundle("messages");

/**
 * Security mapping
 */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", bunlde.getString("User.login.incorrect"));
        }

        if (logout != null) {
            model.addObject("msg", bunlde.getString("User.logout"));
        }

        filterService.InitFilter(); //Initialize default filter values
        model.setViewName("login");

        return model;

    }

/**for 403 access denied page
 *
 */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}
