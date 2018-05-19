package com.wangchao.wxsell.dao;

import com.wangchao.wxsell.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
//    @Transactional
    public void save(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("男生最爱");
//        productCategory.setCategoryId(1);
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findOne1(){
        ProductCategory productCategory = productCategoryDao.findOne(1);
//        ProductCategory productCategory = productCategoryDao.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    public void delete(){
        productCategoryDao.delete(4);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list=new ArrayList<>();
        list.add(1);
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(list);
        System.out.println(byCategoryTypeIn.get(0));
    }


}