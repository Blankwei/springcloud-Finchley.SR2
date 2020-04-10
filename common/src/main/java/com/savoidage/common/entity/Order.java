package com.savoidage.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-10 13:16
 * Description: 订单实体
 */
@Data
public class Order implements Serializable {

    @Id
    private Integer orderId; // 订单id

    private Integer buyerUserId; // 买家id

    private String buyerUserName; // 买家名称

    private BigDecimal orderAmount; // 订单金额

    private Integer orderStatus; // 订单状态

    private Integer payStatus; // 支付状态

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间
}
