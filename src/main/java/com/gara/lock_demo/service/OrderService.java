package com.gara.lock_demo.service;

import com.gara.lock_demo.domain.Order;
import org.springframework.stereotype.Service;

/**
 * @description: 订单处理类
 * @author: GaraYing
 * @create: 2018-08-14 14:34
 **/

public interface OrderService {

    public String sendOrder(Order order);
    public String sendOrderByTemplate(Order order);
    public String sendOrderByTemplateThread(Order order);
    public Order findOrderById(String orderId);
}
