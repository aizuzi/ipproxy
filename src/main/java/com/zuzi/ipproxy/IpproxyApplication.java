package com.zuzi.ipproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //开启定时任务
@EnableJpaRepositories
@SpringBootApplication
public class IpproxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpproxyApplication.class, args);
    }

}
