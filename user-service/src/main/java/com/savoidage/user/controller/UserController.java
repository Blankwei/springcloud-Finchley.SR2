package com.savoidage.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-08 16:28
 * Description: UserController
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/getInfo")
    public String getInfo(){
        return "user-service 访问成功...";
    }

}
