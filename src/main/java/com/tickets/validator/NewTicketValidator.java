package com.tickets.validator;

import com.tickets.model.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class NewTicketValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ticket ticket = (Ticket) target;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty.TicketForm.number");

        if(ticket.getNumber()==null || ticket.getNumber()==""){
            errors.rejectValue("number", "NotEmpty.TicketForm.number");
        }

        if(ticket.getTitle()==null || ticket.getTitle()==""){
            errors.rejectValue("title", "NotEmpty.TicketForm.title");
        }

        if(ticket.getCluster()==null || ticket.getCluster()==""){
            errors.rejectValue("cluster", "NotEmpty.TicketForm.cluster");
        }

        if(ticket.getDescription()==null || ticket.getDescription()==""){
            errors.rejectValue("description", "NotEmpty.TicketForm.description");
        }

        if(ticket.getReportedBy()==null || ticket.getReportedBy()==""){
            errors.rejectValue("reportedBy", "NotEmpty.TicketForm.reportedBy");
        }

        if(ticket.getPriority()==null || ticket.getPriority()==""){
            errors.rejectValue("priority", "NotEmpty.TicketForm.priority");
        }


    }

}