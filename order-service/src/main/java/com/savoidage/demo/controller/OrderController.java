package com.savoidage.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-08 16:31
 * Description: OrderController
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @GetMapping("/getInfo")
    public String getInfo(){
        return "order-service 访问成功...";
    }
}
