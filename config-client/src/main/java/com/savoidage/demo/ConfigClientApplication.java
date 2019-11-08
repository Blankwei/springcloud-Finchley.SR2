package com.savoidage.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfigClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
        logger.info("config-client 启动成功...");
    }

}
