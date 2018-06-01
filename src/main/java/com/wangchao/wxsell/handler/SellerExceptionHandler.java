package com.wangchao.wxsell.handler;

import com.wangchao.wxsell.exception.SellException;
import com.wangchao.wxsell.exception.SellerAuthorizeException;
import com.wangchao.wxsell.util.ResultVOUtil;
import com.wangchao.wxsell.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
        System.out.println("拦截登录异常");
        return new ModelAndView("redirect:"+"http://127.0.0.1:8080/sell/seller/order/list");
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
