package com.tickets.model;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public class ChartKeyValue {
    String key;
    String value2;
    String value3;
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

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value) {
        this.value2 = value;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value) {
        this.value3 = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
