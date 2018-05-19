package com.wangchao.wxsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


    @Test
    public void test(){
        String name="wang";
        String password="123";
        log.info("name={},password={}",name,password);
        log.debug("debug....");
        log.info("info2222.....");
        log.error("error2222....");
    }

}
