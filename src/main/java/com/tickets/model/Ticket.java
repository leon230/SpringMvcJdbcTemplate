package com.tickets.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Leon on 2016-07-31.
 */
public class Ticket {
    private int id;
    private String number;
    private String title;
    private String owner;
    private String cluster;
    private Date openDate;
    private Date closeDate;
    private String description;
    private String reportedBy;
    private String priority;
    private String status;
    private String accOwner;

    public Ticket() {
    }

    public Ticket(String number, String title, String owner, String cluster, Date openDate, Date closeDate, String description,
                  String reportedBy, String priority, String status, String accOwner) {
        this.number = number;
        this.title = title;
        this.owner = owner;
        this.cluster = cluster;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.description = description;
        this.reportedBy = reportedBy;
        this.priority = priority;
        this.status = status;
        this.accOwner = accOwner;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setOpenDate(Date date){this.openDate = date;}

    public Date getOpenDate(){return openDate;}

    public void setCloseDate(Date date){this.closeDate = date;}

    public Date getCloseDate(){return closeDate;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {this.description = val;}

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String val) {
        this.reportedBy = val;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String val) {
        this.priority = val;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }

    public String getAccOwner() {
        return accOwner;
    }

    public void setAccOwner(String val) {
        this.accOwner = val;
    }
}
