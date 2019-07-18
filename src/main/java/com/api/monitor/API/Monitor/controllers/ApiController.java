package com.api.monitor.API.Monitor.controllers;

import com.api.monitor.API.Monitor.models.*;
import com.api.monitor.API.Monitor.services.UrlMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(ApiController.class);
    private UrlMonitoringService urlMonitoringService;
    private String testStatusCode, testInitialMessage;
    private UrlRequestRepository urlRequestRepository;
    private UrlStatusRepository urlStatusRepository;

    private Boolean testUrlConnection(Url u) {
        logger.info("Initial url test for {}",u.getUrl());
                try {
            URL url = new URL(u.getProtocol()+"://"+u.getUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(u.getRequestMethod());
            u.setHeaders(con);

        } catch (Exception e) {
                    e.printStackTrace();
                    testInitialMessage = e.getMessage();
                    testStatusCode = "503";
                    return false;
                }
        return true;
    }

    @Autowired
    public ApiController(UrlMonitoringService urlMonitoringService, UrlRequestRepository urlRequestRepository, UrlStatusRepository urlStatusRepository) {
        this.urlMonitoringService = urlMonitoringService;
        this.urlRequestRepository = urlRequestRepository;
        this.urlStatusRepository = urlStatusRepository;
    }

    @RequestMapping(value = "/addUrl", method = RequestMethod.GET)
    public ResponseUrl addUrlToWatch(@RequestBody RequestUrl requestUrl) throws ServletException {

        if(requestUrl.getProtocol() == null || requestUrl.getUrl() == null || requestUrl.getRequestMethod() == null || requestUrl.getName() == null || requestUrl.getTag() == null) {
            throw new ServletException("Need all the parameters");
        }
        Url url = new Url(requestUrl.getUrl(),requestUrl.getRequestMethod(),requestUrl.getProtocol());
        if(!testUrlConnection(url)) {
            return new ResponseUrl(testInitialMessage,new ResponseUrl.Details(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),testStatusCode));
        }
        logger.info("New Api added {}, name {}",requestUrl.getUrl(), requestUrl.getName());
        urlRequestRepository.save(requestUrl);
        //urlStatusRepository.save(new Url(requestUrl.getUrl(),200,"GET","2019.07.18.10.07.54",requestUrl.getProtocol()));
        urlMonitoringService.addUrlToMonitor(url);
        urlMonitoringService.runReadyToMonitorUrlQueue();
        return new ResponseUrl("Url "+requestUrl.getUrl()+" is being monitored",new ResponseUrl.Details(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),"200"));
    }

}
