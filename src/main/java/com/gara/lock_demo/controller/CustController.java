package com.gara.lock_demo.controller;

import com.gara.lock_demo.domain.Order;
import com.gara.lock_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 消费端
 * @author: GaraYing
 * @create: 2018-08-14 14:33
 **/
/*
    接口层：对外开放接口路径及地址 http://127.0.0.1:8080/order/sendOrder?orderid=1
 */
@RestController
@RequestMapping("/order")
public class CustController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestParam(required = true) String orderid){
        Order order = orderService.findOrderById(orderid);
        return order;
    }


    @RequestMapping("/sendOrder")
    @ResponseBody
    public String sendOrder(@RequestParam(required = true) String orderid){
        Order order = orderService.findOrderById(orderid);
        return orderService.sendOrder(order);
    }

    @RequestMapping("/sendOrderByTemplate")
    @ResponseBody
    public String sendOrderByTemplate(@RequestParam(required = true) String orderid){
        Order order = orderService.findOrderById(orderid);
        return orderService.sendOrderByTemplate(order);
    }

    @RequestMapping("/sendOrderByTemplateThread")
    @ResponseBody
    public String sendOrderByTemplateThread(@RequestParam(required = true) String orderid){
        Order order = orderService.findOrderById(orderid);
        return orderService.sendOrderByTemplateThread(order);
    }

}
