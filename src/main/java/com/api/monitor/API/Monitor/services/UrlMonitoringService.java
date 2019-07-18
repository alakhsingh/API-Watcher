package com.api.monitor.API.Monitor.services;

import com.api.monitor.API.Monitor.UrlRunnable;
import com.api.monitor.API.Monitor.models.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class UrlMonitoringService {

    private List<Url> currentMonitoringUrl = new ArrayList<>();
    private List<Url> readyToMonitorUrl = new ArrayList<>();
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private Logger logger = LoggerFactory.getLogger(UrlMonitoringService.class);

    //runs threads for every 2 seconds
    public void runReadyToMonitorUrlQueue() {

        for (int i = 0; i < readyToMonitorUrl.size(); i++) {
            scheduledExecutorService.scheduleAtFixedRate
                    (new UrlRunnable(readyToMonitorUrl.get(i)), 0, 2, TimeUnit.SECONDS);
            logger.info("run {}",readyToMonitorUrl.get(i).getStatus());
        }
        currentMonitoringUrl.addAll(readyToMonitorUrl);
        readyToMonitorUrl.clear();
    }

    public void addUrlToMonitor(Url url) {
        readyToMonitorUrl.add(url);
    }

}
