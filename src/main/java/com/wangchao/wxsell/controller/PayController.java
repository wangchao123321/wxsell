package com.wangchao.wxsell.controller;

import com.wangchao.wxsell.dto.OrderDTO;
import com.wangchao.wxsell.enums.ResultEnum;
import com.wangchao.wxsell.exception.SellException;
import com.wangchao.wxsell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                        @RequestParam("returnUrl") String returnUrl){
        // 1查询订单
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 发起支付

    }
}
