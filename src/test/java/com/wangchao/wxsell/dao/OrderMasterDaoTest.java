package com.wangchao.wxsell.dao;

import com.wangchao.wxsell.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;
    private final String OPENID = "110110";

    @Test
    public void test(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234566");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("幕课网");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(1));

        OrderMaster result = orderMasterDao.save(orderMaster);
    }

    @Test
    public void test2(){

        Page<OrderMaster> byBuyerOpenid = orderMasterDao.findByBuyerOpenid(OPENID, new PageRequest(1, 3));
        Assert.assertNotEquals(0,byBuyerOpenid.getTotalElements());
    }
}
