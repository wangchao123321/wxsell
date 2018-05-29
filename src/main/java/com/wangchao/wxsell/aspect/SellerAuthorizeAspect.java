package com.wangchao.wxsell.aspect;

import com.wangchao.wxsell.constant.CookieConstant;
import com.wangchao.wxsell.constant.RedisConstant;
import com.wangchao.wxsell.exception.SellerAuthorizeException;
import com.wangchao.wxsell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.wangchao.wxsell.controller.Seller*.*(..)) " +
            "&& !execution(public * com.wangchao.wxsell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("cookie中找不到token");
            throw new SellerAuthorizeException();
        }

        String tokenValue=redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("redis中找不到token");
            throw new SellerAuthorizeException();
        }
    }
}
