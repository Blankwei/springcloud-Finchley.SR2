package com.savoidage.demo.api.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-22 20:55
 * Description: 实际场景中根据业务配置多个
 */
public interface MySource {

    String OUT_PUT_1 = "output1";

    String OUT_PUT_2 = "output2";

    @Output(OUT_PUT_1)
    MessageChannel output1();

    @Output(OUT_PUT_2)
    MessageChannel output2();
}
