package com.tickets.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Leon on 2016-07-31.
 */
public class Ticket {
    @NotNull
    private int id;
    @NotNull
    private String number;
    @NotNull
    private String title;
    @NotNull
    private String owner;
    private String cluster;

    public Ticket() {
    }

    public Ticket(String number, String title, String owner, String cluster) {
        this.number = number;
        this.title = title;
        this.owner = owner;
        this.cluster = cluster;
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
}
