package com.savoidage.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DmeoApiApplication {

    private static Logger logger = LoggerFactory.getLogger(DmeoApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DmeoApiApplication.class, args);
        logger.info("demo-api 启动成功...");
    }

}
