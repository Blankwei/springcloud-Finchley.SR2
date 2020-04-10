package com.savoidage.product.receive;

import com.savoidage.common.entity.OrderDetail;
import com.savoidage.common.utils.Constant;
import com.savoidage.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-10 14:15
 * Description: 消息接收处理
 */
@Component
public class ProductReceive {

    private Logger logger = LoggerFactory.getLogger(ProductReceive.class);

    // 创建队列
    @RabbitListener(queuesToDeclare = @Queue(Constant.ORDER_STOCK_DECREASE))
    public void process(String message){
        try {
            @SuppressWarnings("unchecked")
            List<OrderDetail> list = JsonUtil.Json2Object(message, List.class);
            // 库存更减操作
            logger.info("消息队列接收队列消息内容：" + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 自动创建队列 交换机和队列绑定并接收指定key的消息
    @RabbitListener(bindings = @QueueBinding(value = @Queue("TestQueue"),
                                            exchange = @Exchange("testExchange"),
                                            key = "test"))
    public void process1(String message){
        logger.info("TestQueue 接收队列消息 ：" + message);
    }
}
