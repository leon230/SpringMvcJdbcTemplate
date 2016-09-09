package com.tickets.TLD;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.homik on 2016-08-24.
 */
public class CustomTld extends SimpleTagSupport {

    public static boolean contains(List list, Object o){

        return list.contains(o);
    }

    public static String changeClass(String inputStr, Date dateTo, Date dateFrom){
        long diff = dateTo.getTime() - dateFrom.getTime();

        if (inputStr.equals("High")){
                return "phigh";
            }
            else if(diff < 10){
                return "ood";
            }
            else {
                return "thstd";
            }

    }
}
