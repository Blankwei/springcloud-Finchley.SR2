package com.savoidage.demo.fallback;

import com.alibaba.fastjson.JSON;
import com.savoidage.common.entity.Student;
import com.savoidage.common.redis.RedisUtils;
import com.savoidage.demo.service.DemoApiService;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-19 10:44
 * Description: DemoApiFallback
 */
@Component
public class DemoApiFallback implements FallbackFactory<DemoApiService> {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public DemoApiService create(Throwable throwable) {
        return new DemoApiService() {
            @Override
            public String findOne(Integer stuId) {
                // 根据自己的需求写业务逻辑 ...
                Object o = redisUtils.get("student");
                if(null != o){
                    Student student = JSON.parseObject(o.toString(), Student.class);
                    if(null != student && student.getId() == 111){
                        return student.getStudentName();
                    }
                }
                return "网络异常...请稍后尝试!";
            }
        };
    }
}
