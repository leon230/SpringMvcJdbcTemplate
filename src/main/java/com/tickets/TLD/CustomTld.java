package com.tickets.TLD;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.Date;
import java.util.List;

public class CustomTld extends SimpleTagSupport {

    public static boolean contains(List list, Object o){

        return list.contains(o);
    }

    /**
     * Change CSS class for main table.
     * 4 return types :
     * phigh, new, thstd, ood
     */
    public static String changeClass(String strPriority, String strStatus, Date dateTo, Date dateFrom, Date dateOpen){
        //Checking if ticket is not closed and there are 2 days left to solve it
        if (dateFrom != null & dateTo != null & !strStatus.equals("Closed") ) {
            long diff = dateTo.getTime() - dateFrom.getTime();

            if(diff <= 1000*60*60*24*2){  //Date less than 2 days
                return "ood";
            }

        }
        //High priority ticket and it is not closed
        if (strPriority.equals("High") & !strStatus.equals("Closed")){
                return "phigh";
            }
        //New ticket
        else if (dateOpen == null){
            return "new";
        }
        //Closed ticket has standard
        else if(strStatus.equals("Closed")){
            return "thstd";
        }
        else {
            return "thstd";
        }

    }
}
