package org.cdjavaer.learning.mybatis.web;

import org.cdjavaer.learning.mybatis.domain.Order;
import org.cdjavaer.learning.mybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器
 *
 * @author 袁臻
 * 2017/08/25 09:15
 */
@RestController
@RequestMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    /**
     * 订单服务
     */
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order insert(@RequestBody Order order) {
        return orderService.insert(order);
    }

    @GetMapping(value = "{id}")
    public Order select(@PathVariable("id") String id) {
        return orderService.select(id);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") String id) {
        orderService.delete(id);
    }
}
