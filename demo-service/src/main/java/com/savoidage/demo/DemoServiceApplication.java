package com.savoidage.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
        logger.info("demo-service 启动成功...");
    }

}
