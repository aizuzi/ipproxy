package com.zuzi.ipproxy.job;

import com.zuzi.ipproxy.service.PullProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 拉取代理任务
 */
@Component
public class PullProxyJob {
    private final Logger logger = LoggerFactory.getLogger(PullProxyJob.class);

    @Autowired
    PullProxyService service;

    //表示每隔15分钟
    @Scheduled(fixedDelay = 1000*60*15)
    public void fixedRateJob() {

        System.err.println("11111111111111111111");
        logger.info("DanmuMessagePush 间隔10s time:{}", System.currentTimeMillis());
        service.pullProxy();

    }
}
