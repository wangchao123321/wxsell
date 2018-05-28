package com.wangchao.wxsell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.wangchao.wxsell.dto.OrderDTO;
import com.wangchao.wxsell.enums.ResultEnum;
import com.wangchao.wxsell.exception.SellException;
import com.wangchao.wxsell.service.OrderService;
import com.wangchao.wxsell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,PayResponse> map){
        // 1查询订单
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse",payResponse);
        // 发起支付


        return new ModelAndView("pay/create",map);
    }
}
