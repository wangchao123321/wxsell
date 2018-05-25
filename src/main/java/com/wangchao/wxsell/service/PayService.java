package com.wangchao.wxsell.service;

import com.wangchao.wxsell.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);
}
