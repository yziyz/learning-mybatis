package org.cdjavaer.learning.mybatis.service;

import org.cdjavaer.learning.mybatis.domain.Order;
import org.cdjavaer.learning.mybatis.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 订单服务
 *
 * @author 袁臻
 * 2017/08/25 09:10
 */
@Service
public class OrderService {

    /**
     * 订单映射器
     */
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Order select(String id) {
        return orderMapper.select(id);
    }

    public Order insert(Order order) {
        String id = UUID.randomUUID().toString();
        order.setId(id);
        order.setCreatedAt(LocalDateTime.now());
        orderMapper.insert(order);
        return orderMapper.select(id);
    }

    public int delete(String id) {
        return orderMapper.delete(id);
    }
}
