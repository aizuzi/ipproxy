package com.zuzi.ipproxy.job;

import com.zuzi.ipproxy.service.CheckProxyService;
import com.zuzi.ipproxy.service.PullProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 检查代理任务
 */
@Component
public class CheckProxyJob {
    private final Logger logger = LoggerFactory.getLogger(CheckProxyJob.class);

    //    @Autowired
//    DanmuMessageService danmuMessageService;
    @Autowired
    CheckProxyService service;

    //表示每隔3秒
    @Scheduled(fixedDelay = 1000 * 60*10)
    public void fixedRateJob() {
        service.checkProxy();
//        System.err.println("11111111111111111111");
//        logger.info("DanmuMessagePush 间隔10s time:{}", System.currentTimeMillis());
//        danmuMessageService.pushAlert();

    }
}
