package com.wangchao.wxsell.handler;

import com.wangchao.wxsell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        System.out.println("拦截登录异常");
        return new ModelAndView("redirect:"+"http://127.0.0.1:8080/sell/seller/order/list");
    }

}
