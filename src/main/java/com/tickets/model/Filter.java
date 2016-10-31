package com.tickets.model;

import java.util.List;

public class Filter {
    private static List<String> clusters;
    private static List<String> statuses;
    private static List<String> priorities;
    private static String condition;

    public Filter(){
    }

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

    public static String getCondition() {
        return condition;
    }

    public void setCondition() {

        condition = "CLUSTER IN (";
        for (String str: clusters
                ) {
            condition = condition + "'" + str + "',";
        }
        condition = condition .substring(0,condition.length()-1)+ ")";

        condition = condition + " AND STATUS IN (";
        for (String str: statuses
                ) {
            condition = condition + "'" + str + "',";

        }
        condition = condition .substring(0,condition.length()-1)+ ")";

        condition = condition + " AND PRIORITY IN (";
        for (String str: priorities
                ) {
            condition = condition + "'" + str + "',";

        }
        condition = condition .substring(0,condition.length()-1)+ ")";


    }
}