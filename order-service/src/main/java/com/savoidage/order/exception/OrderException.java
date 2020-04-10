package com.savoidage.order.exception;

import com.savoidage.order.enums.ResponseEnum;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-09 17:59
 * Description: 自定义订单异常
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResponseEnum responseEnum){
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }
}
