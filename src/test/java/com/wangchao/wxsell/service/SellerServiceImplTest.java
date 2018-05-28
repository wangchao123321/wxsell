package com.wangchao.wxsell.service;

import com.wangchao.wxsell.domain.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    private static final String OPENID="abc";

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfoByOpenid = sellerService.findSellerInfoByOpenid(OPENID);
        System.out.println(sellerInfoByOpenid);
    }
}