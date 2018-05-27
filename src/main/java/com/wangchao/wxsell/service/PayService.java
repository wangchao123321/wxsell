package com.wangchao.wxsell.service;

import com.lly835.bestpay.model.PayResponse;
import com.wangchao.wxsell.dto.OrderDTO;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);
}
