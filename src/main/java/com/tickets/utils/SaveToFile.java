package com.tickets.utils;

import com.tickets.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;

/**
 * Created by Lukasz.Homik on 2016-09-06.
 */
public class SaveToFile {
    private String fileTo;
    private List<Ticket> listToSave;

    public SaveToFile(List<Ticket> listToSave, String fileTo) {
        this.listToSave = listToSave;
        this.fileTo = fileTo;
    }


    @Autowired
    public void saveFile()
    {
        String str;
        try
        {
            FileWriter fw = new FileWriter(fileTo);
            for (Ticket ticket: listToSave
                    ) {
                str = ticket.getNumber() + "," + ticket.getTitle() + "," + ticket.getOwner();
                str = str +  "\n";
                fw.write(str);
            }

            fw.close();

        } catch(IOException exc) {
            System.out.println("I/O Error: " + exc);
        }

    }

}
