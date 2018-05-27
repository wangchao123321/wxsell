package com.wangchao.wxsell.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;


    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAll1() {
    }

    @Test
    public void save() {
    }

    @Test
    public void onSale() {
        productInfoService.onSale("123456");
    }

    @Test
    public void offSale() {
        productInfoService.offSale("123456");
    }
}