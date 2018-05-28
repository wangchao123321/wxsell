package com.wangchao.wxsell.dao;

import com.wangchao.wxsell.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openId);
}
