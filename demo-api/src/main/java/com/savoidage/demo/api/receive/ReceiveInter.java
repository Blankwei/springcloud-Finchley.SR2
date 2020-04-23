package com.savoidage.demo.api.receive;

import org.springframework.messaging.Message;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-22 18:39
 * Description: 消息接收方
 */
public interface ReceiveInter {

    public void receive(Message<String> message);

}
