package com.tickets.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sun.security.krb5.internal.Ticket;

/**
 * Created by Leon on 2016-07-31.
 */
public class NewTicketValidator implements Validator{

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return Ticket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ticket ticket = (Ticket)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "required.number","Field Number is required.");

//        if("NONE".equals(cust.getCountry())){
//            errors.rejectValue("country", "required.country");
//        }
    }

}
