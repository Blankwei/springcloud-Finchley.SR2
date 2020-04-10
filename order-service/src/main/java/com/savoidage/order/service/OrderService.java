package com.savoidage.order.service;

import com.savoidage.common.dto.OrderDTO;
import com.savoidage.common.entity.OrderDetail;
import com.savoidage.common.entity.Product;
import com.savoidage.common.redis.RedisUtils;
import com.savoidage.common.utils.Constant;
import com.savoidage.common.utils.JsonUtil;
import com.savoidage.order.enums.ResponseEnum;
import com.savoidage.order.exception.OrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-09 17:19
 * Description: OrderService
 */
@Service("orderService")
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 新增订单
     * @param orderDTO 订单内容
     * @return 订单
     */
    public OrderDTO createOrder(OrderDTO orderDTO) {
        List<Integer> collect = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<Product> productList = getProductList(collect);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            for(Product product:productList){
                // 库存效验
                if(orderDetail.getProductId().equals(product.getProductId())){
                    if(orderDetail.getProductQuantity()>product.getProductStock()){
                        logger.error("订单中存在库存不足的商品：【商品id：" + product.getProductId() + "】-【库存数量：" + product.getProductStock() + "】");
                        throw new OrderException(ResponseEnum.PRODUCT_NOT_ENOUGH);
                    }
                }
            }
        }
        // 订单详情入库处理(省略)
        // 异步减库存
        amqpTemplate.convertAndSend(Constant.ORDER_STOCK_DECREASE,JsonUtil.Object2Json(orderDTO.getOrderDetailList()));
        // 订单入库处理(省略)
        return orderDTO;
    }

    // 根据id获取商品信息
    private List<Product> getProductList(List<Integer> productIds){
        List<Product> productList = new ArrayList<>(productIds.size());
        for(Integer productId:productIds){
            Object get = redisUtils.hmGet(Constant.PRODUCT_DATA, JsonUtil.Object2Json(productId));
            if(null != get){
                try {
                    Product product = JsonUtil.Json2Object(get.toString(), Product.class);
                    productList.add(product);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return productList;
    }
}
