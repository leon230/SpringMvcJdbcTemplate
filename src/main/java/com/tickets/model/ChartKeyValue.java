package com.tickets.model;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public class ChartKeyValue {
    String key;
    String value;

    public ChartKeyValue(){

    }
    public ChartKeyValue(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
