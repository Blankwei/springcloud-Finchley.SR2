package com.savoidage.product.Controller;

import com.savoidage.common.entity.Product;
import com.savoidage.common.redis.RedisUtils;
import com.savoidage.common.utils.Constant;
import com.savoidage.common.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-10 14:44
 * Description: 商品控制器
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/create")
    public String createProduct(){
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("海飞丝洗发水");
        product1.setProductDescription("一款神奇的去头油洗发水,从油头怪变成柳树枝");
        product1.setProductStock(100);
        product1.setProductPrice(new BigDecimal("10"));
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());
        redisUtils.hmSet(Constant.PRODUCT_DATA,JsonUtil.Object2Json(product1.getProductId()),JsonUtil.Object2Json(product1));
        Product product2 = new Product();
        product2.setProductId(2);
        product2.setProductName("巴黎欧莱雅");
        product2.setProductDescription("一款神奇的去头油洗发水,从油头怪变成柳树枝");
        product2.setProductStock(200);
        product2.setProductPrice(new BigDecimal("14"));
        product2.setCreateTime(new Date());
        product2.setUpdateTime(new Date());
        redisUtils.hmSet(Constant.PRODUCT_DATA,JsonUtil.Object2Json(product2.getProductId()),JsonUtil.Object2Json(product2));
        Product product3 = new Product();
        product3.setProductId(3);
        product3.setProductName("清扬男士");
        product3.setProductDescription("一款神奇的去头油洗发水,从油头怪变成柳树枝");
        product3.setProductStock(120);
        product3.setProductPrice(new BigDecimal("13"));
        product3.setCreateTime(new Date());
        product3.setUpdateTime(new Date());
        redisUtils.hmSet(Constant.PRODUCT_DATA,JsonUtil.Object2Json(product3.getProductId()),JsonUtil.Object2Json(product3));
        return "success";
    }

    /**
     * 测试自动创建队列后接收指定key的信息
     * @return
     */
    @GetMapping("/testmq")
    public String testBindInfo(){
        amqpTemplate.convertAndSend("testExchange","test","this is a test info");
        return "success";
    }

}
