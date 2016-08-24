package com.tickets.TLD;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-08-24.
 */
public class TextTLD extends SimpleTagSupport {

    public static boolean contains(List list, Object o){

        return list.contains(o);
    }
}
