package com.savoidage.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-07 18:17
 * Description: TestController
 */
@RestController
public class TestController {

    @Value("${application.name}")
    private String value;

    @RequestMapping("/hi")
    public String hello() {
        return "hi" + value;
    }
}
