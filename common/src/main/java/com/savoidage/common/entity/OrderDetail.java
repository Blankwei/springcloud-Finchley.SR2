package com.savoidage.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-10 13:11
 * Description: 订单明细实体
 */
@Data
public class OrderDetail implements Serializable {

    @Id
    private Integer orderDetailId; // 订单明细id

    private Integer productId; // 商品id

    private String productName; // 商品名称

    private BigDecimal productPrice; // 商品价格

    private Integer productQuantity; // 商品数量

}
