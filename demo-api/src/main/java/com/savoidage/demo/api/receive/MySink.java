package com.savoidage.demo.api.receive;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-22 20:59
 * Description: todo
 */
public interface MySink {


    String INPUT_1 = "input1";

    String INPUT_2 = "input2";

    @Input(INPUT_1)
    SubscribableChannel input1();

    @Input(INPUT_2)
    SubscribableChannel input2();
}
