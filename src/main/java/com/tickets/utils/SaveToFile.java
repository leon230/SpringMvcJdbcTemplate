package com.tickets.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * Created by Lukasz.Homik on 2016-09-06.
 */
public class SaveToFile {
    private String fileFrom = "test.txt";

    @Autowired
    public void saveFile()
    {
        String str;
        BufferedReader br =
                new BufferedReader(new StringReader("Some string stop"));

//        System.out.println("Enter text ('stop' to quit).");
        try (FileWriter fw = new FileWriter(fileFrom))
        {
            do {
//                System.out.print(": ");
                str = br.readLine();
                if(str.compareTo("stop") == 0) break;
                str = str + "\r\n"; // add newline
                fw.write(str);
            } while(str.compareTo("stop") != 0);
        } catch(IOException exc) {
//            System.out.println("I/O Error: " + exc);
        }
    }

}




