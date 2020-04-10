package com.savoidage.order.controller;

import com.savoidage.common.base.ResponseModel;
import com.savoidage.common.dto.OrderDTO;
import com.savoidage.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-08 16:31
 * Description: OrderController
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单(主要模拟异步操作库存的步骤)
     * @param orderDTO 订单内容
     * @return 模组结果
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseModel createOrder(@RequestBody OrderDTO orderDTO){
        ResponseModel responseModel = ResponseModel.getSuccess();
        // 参数效验

        // 新增订单
        OrderDTO dto = orderService.createOrder(orderDTO);
        responseModel.addData("orderDTO",dto);
        return responseModel;
    }

}
