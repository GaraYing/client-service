package com.gara.lock_demo.mapper;

import com.gara.lock_demo.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: Order接口
 * @author: GaraYing
 * @create: 2018-08-14 10:40
 **/

@Mapper
public interface OrderMapper {

    Order findOrderById(String orderId);
}
