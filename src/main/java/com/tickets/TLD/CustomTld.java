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

    public static String changeClass(String strPriority, String strStatus, Date dateTo, Date dateFrom){
        if (dateFrom != null & dateTo != null & !strStatus.equals("Closed") ) {
            long diff = dateTo.getTime() - dateFrom.getTime();

            if(diff <= 1000*60*60*24*2){
                return "ood";
            }

        }
        if (strPriority.equals("High") & !strStatus.equals("Closed")){
                return "phigh";
            }
        else if (dateTo == null){
            return "new";
        }
        else if(strStatus.equals("Closed")){
            return "thstd";
        }
        else {
            return "thstd";
        }

    }
}
