package com.tickets.validator;

import com.tickets.model.Filter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class TicketFilterValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Filter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Filter filter = (Filter) target;

        if(filter.getPriorities() == null || filter.getPriorities().isEmpty()){
            errors.rejectValue("priorities", "NotEmpty.filter.priorities");
        }

        if(filter.getClusters() == null || filter.getClusters().isEmpty()){
            errors.rejectValue("clusters", "NotEmpty.filter.clusters");
        }

        if(filter.getStatuses() == null || filter.getStatuses().isEmpty()){
            errors.rejectValue("statuses", "NotEmpty.filter.statuses");
        }


    }

}