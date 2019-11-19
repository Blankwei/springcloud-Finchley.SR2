package com.savoidage.demo.controller;

import com.savoidage.common.entity.Student;
import com.savoidage.demo.service.DemoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 16:25
 * Description: DemoApiController
 */
@RequestMapping(value = "/api")
@RestController
public class DemoApiController {

    @Autowired
    private DemoApiService demoApiService;

    @GetMapping(value = "/student/{stuId}")
    public String findOne(@PathVariable("stuId") Integer stuId){
        if(null == stuId){
            return "id为空";
        }
        String studentName = demoApiService.findOne(stuId);
        return "获取学生的名字：" + studentName;
    }
}
