package com.api.monitor.API.Monitor;

import com.api.monitor.API.Monitor.models.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UrlRunnable implements Runnable {

    private Url url;
    private Logger logger = LoggerFactory.getLogger(UrlRunnable.class);
    private int status = -1;

    public UrlRunnable(Url url) {
        this.url = url;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url.getProtocol()+"://"+this.url.getUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(this.url.getRequestMethod());
            this.url.setHeaders(con);
            status =  con.getResponseCode();
        } catch (ConnectException ex) {
            status = HttpStatus.SERVICE_UNAVAILABLE.value();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            e.printStackTrace();
        } finally {
            this.url.setTimestamp(getTimestamp());
        }
        logger.info("url {}, current status {}, last url status {}",this.url.getUrl(), status, this.url.getResponseCode());
        if (this.url.getResponseCode() == null) {
            this.url.setTimestampSinceLastChange(getTimestamp());
        } else if ( status != this.url.getResponseCode()) {
            this.url.setTimestampSinceLastChange(getTimestamp());
            logger.info("Response code status changed for {}",this.url.getUrl());
        }
        this.url.setResponseCode(status);
    }
}
