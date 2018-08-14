package com.gara.lock_demo.service.impl;

import com.gara.lock_demo.domain.Order;
import com.gara.lock_demo.mapper.OrderMapper;
import com.gara.lock_demo.service.OrderService;
import com.gara.lock_demo.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO
 * @author: GaraYing
 * @create: 2018-08-14 14:36
 **/

@Service
public class OrderServiceImpl implements OrderService {

    public static String url = "http://127.0.0.1:8090/bank/handleOrder?orderid=";

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TransService transService;
//    @Autowired
//    private RestTemplate restTemplate;

    // 事务保证数据一致性？
    @Transactional
    @Override
    public String sendOrder(Order order) {
        String orderId = order.getOrderId();
        String flag = "-1";
        flag = transService.invoke(url,orderId);
        order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(flag);
        return flag;
    }

    @Override
    public Order findOrderById(String orderId) {
        return orderMapper.findOrderById(orderId);
    }
}
