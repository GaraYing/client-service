package com.gara.lock_demo.service.impl;

import com.gara.lock_demo.domain.Order;
import com.gara.lock_demo.mapper.OrderMapper;
import com.gara.lock_demo.service.OrderService;
import com.gara.lock_demo.service.TransService;
import org.apache.logging.log4j.core.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO
 * @author: GaraYing
 * @create: 2018-08-14 14:36
 **/

@Service
public class OrderServiceImpl implements OrderService {

    public static String url = "http://127.0.0.1:8090/bank/handleOrder?orderid=";
    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TransService transService;
    @Autowired
    private TransactionTemplate template;

    /**
     * @Description: 事务保证数据一致性？一直占用连接
     * @Param: [order]
     * @return: java.lang.String
     * @Author: GaraYing
     * @Date: 2018/8/15 9:45
     */
//    @Transactional
    @Override
    public String sendOrder(Order order) {
        String orderId = order.getOrderId();
        String flag = "-1";
        flag = transService.invoke(url, orderId);// 调用远程发货接口，10s之后才能返回
        order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(flag);
        return flag;
    }

    /**
     * @Description: 编程事务，保证事务一致性
     * @Param: [order]
     * @return: java.lang.String
     * @Author: GaraYing
     * @Date: 2018/8/15 9:37
     */
    public String sendOrderByTemplate(Order order) {
        String orderId = order.getOrderId();

        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderStatus("4");//订单处理中
                orderMapper.update(order);
                return null;
            }
        });
        // 调用发货接口
        String flag = transService.invoke(url, orderId);// 占连接

        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Order orderFin = new Order();
                orderFin.setOrderId(orderId);
                orderFin.setOrderStatus(flag);//根据发货接口返回存到数据库中
                orderMapper.update(orderFin);
                return null;
            }
        });

        return flag;
    }

    /**
     * @Description: 基于状态机的乐观锁
     * @Param: [order]
     * @return: java.lang.String
     * @Author: GaraYing
     * @Date: 2018/8/15 9:35
     */
    @Override
    public String sendOrderByTemplateThread(Order order) {
        String orderId = order.getOrderId();
        // 只有第一个操作返回true,其他返回false
        Boolean lock = template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderStatus("4");//订单处理中
                order.setVersion(0);
//                orderMapper.update(order);
                return 1 == orderMapper.updateByVersion(order);//受影响的记录数
            }
        });

        if (lock) {
            // 只允许一个线程发货,其他全部拦截
            String flag = transService.invoke(url, orderId);
            template.execute(new TransactionCallback<Object>() {
                @Override
                public Object doInTransaction(TransactionStatus transactionStatus) {
                    Order orderFin = new Order();
                    orderFin.setOrderId(orderId);
                    orderFin.setOrderStatus(flag);//订单处理中
                    orderFin.setVersion(1);
//                    orderMapper.update(order);
                    orderMapper.updateByVersion(orderFin);//受影响的记录数
                    return null;
                }
            });
        } else {
            logger.error("lockFail************" + order.getOrderId());
        }
        return null;
    }
    
    /** 
    * @Description: 获取某条订单
    * @Param: [orderId]
    * @return: com.gara.lock_demo.domain.Order
    * @Author: GaraYing
    * @Date: 2018/8/15 13:48
    */
    @Override
    public Order findOrderById(String orderId) {
        return orderMapper.findOrderById(orderId);
    }
}
