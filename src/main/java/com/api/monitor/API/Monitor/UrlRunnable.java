package com.api.monitor.API.Monitor;

import com.api.monitor.API.Monitor.models.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UrlRunnable implements Runnable {

    private Url url;
    private Logger logger = LoggerFactory.getLogger(UrlRunnable.class);

    public UrlRunnable(Url url) {
        this.url = url;
    }

    private void setHeaders(HttpURLConnection con) throws IOException {
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Connection", "keep-alive");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.flush();
        out.close();
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url.getUrlAddress());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(this.url.getRequestMethod());
            setHeaders(con);
            this.url.setStatus(con.getResponseCode());
        } catch (ConnectException ex) {
            this.url.setStatus(503);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.url.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        }
    }
}
