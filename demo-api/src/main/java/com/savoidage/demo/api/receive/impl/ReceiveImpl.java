package com.savoidage.demo.api.receive.impl;

import com.alibaba.fastjson.JSONObject;
import com.savoidage.common.entity.Student;
import com.savoidage.demo.api.receive.MySink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-22 18:42
 * Description: 接收方实现类
 */
@Component
// 绑定多个输入流 @EnableBinding(MySink.class,YourSink.class)
@EnableBinding(MySink.class)
public class ReceiveImpl {


    @StreamListener(MySink.INPUT_1)
    public void receive(Message<String> message) {
        System.out.println("接收到的消息：" + JSONObject.toJSONString( message.getPayload()));
    }

    @StreamListener(MySink.INPUT_2)
    public void receive2(Student student){
        System.out.println("接收到的消息：" + student.getStudentName());
    }
}
