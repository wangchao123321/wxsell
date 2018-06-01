package com.wangchao.wxsell.controller;

import com.wangchao.wxsell.domain.ProductCategory;
import com.wangchao.wxsell.domain.ProductInfo;
import com.wangchao.wxsell.service.ProductCategoryService;
import com.wangchao.wxsell.service.ProductInfoService;
import com.wangchao.wxsell.util.ResultVOUtil;
import com.wangchao.wxsell.vo.ProductInfoVO;
import com.wangchao.wxsell.vo.ProductVO;
import com.wangchao.wxsell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(){

        //查询所有上架商品
        List<ProductInfo> productInfoList=productInfoService.findUpAll();
        //查询类目
        List<Integer> categoryTypeList=productInfoList.stream().map(productInfo -> productInfo.getCategoryType()).collect(Collectors.toList());
        for (Integer integer : categoryTypeList) {
            System.out.println(integer);
        }
        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }


}
