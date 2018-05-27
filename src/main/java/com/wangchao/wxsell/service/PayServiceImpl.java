package com.wangchao.wxsell.service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.wangchao.wxsell.dto.OrderDTO;
import com.wangchao.wxsell.enums.ResultEnum;
import com.wangchao.wxsell.exception.SellException;
import com.wangchao.wxsell.util.MathUtil;
import com.wangchao.wxsell.util.serializer.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NAME="微信点餐订单";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest=new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 request{}",payRequest);
        PayResponse payResponse=bestPayService.pay(payRequest);
        log.info("【微信支付response】={}",payResponse);
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1验证签名
        // 2支付状态
        // 3支付金额
        // 4支付人(下单人 == 支付人)

        PayResponse payResponse=bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知, payResponse={}", JsonUtil.toJson(payResponse));

        OrderDTO orderDTO=orderService.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if(orderDTO==null){
            log.error("订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("金额不一致");
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MOENY_VERIFY_ERROR);
        }

        // 修改支付状态
        orderService.paid(orderDTO);

        return payResponse;
    }
}
