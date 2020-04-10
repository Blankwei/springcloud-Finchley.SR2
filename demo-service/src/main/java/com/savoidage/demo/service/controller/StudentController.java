package com.savoidage.demo.service.controller;

import com.alibaba.fastjson.JSON;
import com.savoidage.common.entity.Student;
import com.savoidage.common.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 17:51
 * Description: StudentController
 */
@RequestMapping(value = "/student")
@RestController
public class StudentController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/findOne")
    public String findOne(@RequestParam("stuId") Integer stuId){
        if(null == stuId){
            return "请输入学生id.";
        }
        Object data = redisUtils.get("student");
        if(null != data){
            Student student = JSON.parseObject(data.toString(), Student.class);
            if(null != student){
                if(stuId.equals(student.getId())){
                    return student.getStudentName();
                }
            }
        }
        return "未查到该学生的名字.";
    }
}
