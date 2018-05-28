package com.wangchao.wxsell.service;

import com.wangchao.wxsell.domain.SellerInfo;

public interface SellerService {

    /**
     * 返回查询卖家端消息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
