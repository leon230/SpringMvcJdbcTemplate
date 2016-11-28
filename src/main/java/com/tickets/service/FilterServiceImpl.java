package com.tickets.service;

import com.tickets.model.Filter;
import com.tickets.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lukasz.Homik on 2016-11-28.
 */
public class FilterServiceImpl implements FilterService {
    @Autowired
    Filter ticketFilter;

    @Override
    public void InitFilter() {
        Ticket t = new Ticket();
        ticketFilter.setClusters(t.getClustersList());
        ticketFilter.setPriorities(t.getPrioritiesList());
        ticketFilter.setStatuses(t.getStatusesList());
        ticketFilter.setCondition();
    }
}
