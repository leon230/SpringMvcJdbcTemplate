package com.tickets.utils;

import com.tickets.model.Ticket;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by lukasz.homik on 2017-01-23.
 */
public class DownloadFile {

    public static void downloadCSV(HttpServletRequest request, HttpServletResponse response, List<Ticket> listTicket) throws IOException {

        int BUFFER_SIZE = 4096;
//		ServletContext context = request.getServletContext();
        ServletContext context = request.getSession().getServletContext();
        String appPath = context.getRealPath("");
        String filePath = "/Export.csv";
        String fullPath = appPath + filePath;

        SaveToFile sv = new SaveToFile(listTicket,fullPath);
        sv.saveFile();

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}
