package com.tickets.validator;

import com.tickets.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sun.util.resources.cldr.kea.TimeZoneNames_kea;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class NewTicketValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ticket user = (Ticket) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty.TicketForm.number");

        if(user.getNumber()==null || user.getNumber()==""){
            errors.rejectValue("number", "NotEmpty.TicketForm.number");
        }


//        if(user.getNumber()==null || user.getNumber() == ""){
//            errors.rejectValue("number", "NotEmpty.TicketForm.number");
//        }


    }

}