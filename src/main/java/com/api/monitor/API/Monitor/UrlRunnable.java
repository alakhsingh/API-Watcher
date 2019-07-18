package com.api.monitor.API.Monitor;

import com.api.monitor.API.Monitor.models.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public void run() {
        try {
            URL url = new URL(this.url.getUrlAddress());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(this.url.getRequestMethod());
            this.url.setHeaders(con);
            logger.info("url {}, status {}",this.url.getUrlAddress(), con.getResponseCode());
            this.url.setResponseCode(con.getResponseCode());
        } catch (ConnectException ex) {
            this.url.setResponseCode(503);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.url.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        }
    }
}
