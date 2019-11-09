package com.savoidage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.savoidage.common.entity.Student;
import com.savoidage.common.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-08 16:31
 * Description: OrderController
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${spring.redis.database}")
    private String redisValue;

    @GetMapping("/getInfo")
    public String getInfo(){
        return "order-service 访问成功..." + "redisValue=" + redisValue;
    }

    @GetMapping("/getString")
    public String getString(){
        redisUtils.set("orderInfo","this is order service send message.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String info = (String) redisUtils.get("orderInfo");
        return "order-service 访问成功..." + "测试存入redis中的记录[" + info + "]";
    }

    @GetMapping("/getObject/{studentName}")
    public String getObject(@PathVariable("studentName") String studentName){
        Student student = new Student();
        student.setId(1);
        student.setStudentName(studentName);
        student.setSex(false);
        student.setAge(21);
        student.setPhone("15551236078");
        student.setEmail("592232644@qq.com");
        student.setAddress("浙江省杭州市西湖区天目山路支付宝大楼隔壁");
        Long now = System.currentTimeMillis();
        student.setCreateDate(now);
        student.setUpdateDate(now);
        boolean set = redisUtils.set("student", JSON.toJSONString(student));
        if(set){
            Student stu = JSON.parseObject(redisUtils.get("student").toString(),Student.class);
            return "存入学生信息至redis success! 名字为：" + stu.getStudentName();
        }
        return "存入学生信息至redis failed ...";
    }
}
