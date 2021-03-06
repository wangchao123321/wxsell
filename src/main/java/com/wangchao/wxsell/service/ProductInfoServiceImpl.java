package com.wangchao.wxsell.service;

import com.wangchao.wxsell.dao.ProductInfoDao;
import com.wangchao.wxsell.domain.ProductInfo;
import com.wangchao.wxsell.dto.CartDTO;
import com.wangchao.wxsell.enums.ProductStatusEnum;
import com.wangchao.wxsell.enums.ResultEnum;
import com.wangchao.wxsell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo=productInfoDao.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoDao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=productInfoDao.findOne(productId);

        if(productInfo==null){

        }

        if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){

        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo=productInfoDao.save(productInfo);

        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=productInfoDao.findOne(productId);
        if(productInfo==null){
        }

        if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){

        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo=productInfoDao.save(productInfo);
        return productInfo;
    }
}
