package com.api.monitor.API.Monitor.controllers;

import com.api.monitor.API.Monitor.models.RequestUrl;
import com.api.monitor.API.Monitor.models.ResponseUrl;
import com.api.monitor.API.Monitor.services.UrlMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(ApiController.class);
    private UrlMonitoringService urlMonitoringService;

    @Autowired
    public ApiController(UrlMonitoringService urlMonitoringService) {
        this.urlMonitoringService = urlMonitoringService;
    }

    @RequestMapping(value = "/addUrl", method = RequestMethod.GET)
    public ResponseUrl addUrlToWatch(@RequestBody RequestUrl requestUrl) throws ServletException {

        if(requestUrl.getUrl() == null || requestUrl.getRequestMethod() == null || requestUrl.getName() == null || requestUrl.getTag() == null) {
            throw new ServletException("Need all the parameters");
        }
        logger.info("New Api added {}, name {}",requestUrl.getUrl(), requestUrl.getName());

        return new ResponseUrl("Url "+requestUrl.getUrl()+" is being monitored",new ResponseUrl.Details(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),"200"));
    }

}
