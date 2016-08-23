package com.tickets.controller;

import java.util.ArrayList;
import java.util.List;

import com.tickets.model.Filter;
import com.tickets.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/filter**")
public class MemberController {

    @RequestMapping(value = "/filter**", method = RequestMethod.GET)
    public String initForm(Model model) {
        Filter filter = new Filter();
        //List<String> preCheckedVals = new ArrayList<String>();
        //preCheckedVals.add("Reporting");
        //filter.setCourses(preCheckedVals);
        model.addAttribute("filter", filter);
        List<String> courses = new ArrayList<String>();
//        courses = Ticket.getClustersList();
        courses.addAll(Ticket.getClustersList());
        model.addAttribute("courses", courses);
        return "filter/TicketFilter";
    }
//
//    @RequestMapping(value = "/filter**", method = RequestMethod.GET)
//    public ModelAndView initForm() {
//
//        ModelAndView model = new ModelAndView();
//
//        Filter member = new Filter();
//        List<String> preCheckedVals = new ArrayList<String>();
//        preCheckedVals.add("Yoga");
//        member.setCourses(preCheckedVals);
//        model.addObject("member", member);
//        List<String> courses = new ArrayList<String>();
//        courses.add("Yoga");
//        courses.add("Stretching");
//        courses.add("Pilates");
//        courses.add("Aerobic");
//        courses.add("Oriental");
//        model.addObject("courses", courses);
//        model.setViewName("filter/TicketFilter");
//        return model;
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String submitForm(Model model, Filter member,
//                             BindingResult result) {
//        model.addAttribute("member", member);
//        return "member";
//    }

    @RequestMapping(value = "/ApplyFilter", method = RequestMethod.POST)
    public ModelAndView CheckForm(@ModelAttribute("filter") Filter filter, BindingResult result
            , ModelAndView model) {
            model.addObject("filter", filter);
            return new ModelAndView("redirect:/");

        //return "redirect:/";
    }

}