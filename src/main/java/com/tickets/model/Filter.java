package com.tickets.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION,
        proxyMode= ScopedProxyMode.TARGET_CLASS)
public class Filter {

    private static List<String> clusters;
    private static List<String> statuses;
    private static List<String> priorities;
    private static String condition;



    public List<String> getClusters() {
        return clusters;
    }
    public List<String> getPriorities() {
        return priorities;
    }
    public List<String> getStatuses() {
        return statuses;
    }


    public void setClusters(List<String> clusters) {
        this.clusters = clusters;
    }
    public void setPriorities(List<String> priorities) {
        this.priorities = priorities;
    }
    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition() {

        condition = "CLUSTER IN (";

        for (String str: clusters
             ) {
            condition = condition + "'" + str + "',";

        }
        condition = condition .substring(0,condition.length()-1)+ ")";




    }
}