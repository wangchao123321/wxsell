package com.wangchao.wxsell.dao;

import com.wangchao.wxsell.domain.SellerInfo;
import com.wangchao.wxsell.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void testSave(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        sellerInfoDao.save(sellerInfo);
    }

    @Test
    public void findByOpenId() {
        SellerInfo abc = sellerInfoDao.findByOpenid("abc");
        System.out.println(abc);
    }
}