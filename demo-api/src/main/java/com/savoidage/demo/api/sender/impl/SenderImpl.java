package com.savoidage.demo.api.sender.impl;

import com.savoidage.common.entity.Student;
import com.savoidage.demo.api.sender.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-22 18:35
 * Description: 消息发送方实现类
 */
@EnableBinding(MySource.class)
public class SenderImpl {

    @Autowired
    @Output(MySource.OUT_PUT_1)
    private MessageChannel output1;

    @Autowired
    @Output(MySource.OUT_PUT_2)
    private MessageChannel output2;

    public void send(Object o) {
        this.output1.send(MessageBuilder.withPayload(o).build());
    }

    public void send2(Student student){
        this.output2.send(MessageBuilder.withPayload(student).build());
    }
}
