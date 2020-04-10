package com.savoidage.common.dto;

import com.savoidage.common.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-10 13:18
 * Description: 订单DTO
 */
@Data
public class OrderDTO implements Serializable {

    private Integer orderId; // 订单id

    private Integer orderStatus; // 订单状态

    private Integer payStatus; // 支付状态

    private List<OrderDetail> orderDetailList; // 多个订单明细
}
