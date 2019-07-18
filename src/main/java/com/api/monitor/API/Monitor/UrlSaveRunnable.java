package com.api.monitor.API.Monitor;

import com.api.monitor.API.Monitor.models.Url;
import com.api.monitor.API.Monitor.models.UrlStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UrlSaveRunnable implements Runnable {

    private List<Url> currentMonitoringUrl;
    private UrlStatusRepository urlStatusRepository;
    private Logger logger = LoggerFactory.getLogger(UrlSaveRunnable.class);

    public UrlSaveRunnable(List<Url> currentMonitoringUrl, UrlStatusRepository urlStatusRepository) {
        this.currentMonitoringUrl = currentMonitoringUrl;
        this.urlStatusRepository = urlStatusRepository;
    }

    @Override
    public void run() {
        if(currentMonitoringUrl.size() > 0)
        {
            logger.info("Saving current monitoring list {} ",currentMonitoringUrl.size());
            urlStatusRepository.saveAll(currentMonitoringUrl);
        }
    }
}
