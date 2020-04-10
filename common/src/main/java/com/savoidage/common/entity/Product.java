package com.savoidage.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-09 17:24
 * Description: 产品实体
 */
@Data
public class Product implements Serializable {

    @Id
    private Integer productId; // 商品id
    private String productName; // 商品名称
    private String productDescription; // 描述
    private Integer productStock; // 库存
    private BigDecimal productPrice; // 价格
    private Date createTime; // 新增时间
    private Date updateTime; // 更新时间
}
