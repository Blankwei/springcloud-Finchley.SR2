package com.savoidage.order.enums;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-09 18:01
 * Description: 响应枚举
 */
public enum ResponseEnum {

    /**
     * 订单错误
     */
    ORDER_ERROE(501,"订单错误"),

    /**
     * 参数错误
     */
    PARAM_ERROR(502,"参数错误"),

    /**
     * 库存不足
     */
    PRODUCT_NOT_ENOUGH(503,"库存不足"),
    ;

    // 状态码
    private Integer code;

    // 信息
    private String message;

    ResponseEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }
}
