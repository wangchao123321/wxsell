package com.wangchao.wxsell.service;

import com.wangchao.wxsell.domain.OrderDetail;
import com.wangchao.wxsell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private static final String BUYER_OPENID="1101110";

    @Test
    public void create() {

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("12345678912");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("123459");
        orderDetail.setProductQuantity(1);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(4);

        OrderDetail o3=new OrderDetail();
        o3.setProductId("123457");
        o3.setProductQuantity(6);

        OrderDetail o4=new OrderDetail();
        o4.setProductId("123458");
        o4.setProductQuantity(15);

        orderDetailList.add(orderDetail);
        orderDetailList.add(o2);
        orderDetailList.add(o3);
        orderDetailList.add(o4);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result=orderService.create(orderDTO);
        log.info("创建订单 result={}",result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO=orderService.findOne("1526913231941994245");
        log.info("订单 {}",orderDTO);
    }

    @Test
    public void findList() {

        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderDTO> page=orderService.findList(BUYER_OPENID,pageRequest);
        log.info("详情 {}"+page);

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.findOne("1526913335465482654");
        OrderDTO result = orderService.cancel(orderDTO);
        log.info("取消结果 {}",result);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne("1526907302445412238");
        OrderDTO result = orderService.finish(orderDTO);
        log.info("完结订单结果 {}",result);
    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.findOne("1526907302445412238");
        OrderDTO result = orderService.paid(orderDTO);
        log.info("完结订单结果 {}",result);
    }

    @Test
    public void findList1() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderDTO> page=orderService.findList(pageRequest);
        log.info("详情 {}"+page);
    }
}