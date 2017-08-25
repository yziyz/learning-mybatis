package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.cdjavaer.learning.mybatis.domain.Order;
import org.cdjavaer.learning.mybatis.service.OrderSqlProvider;

import java.util.List;

/**
 * 订单映射器
 *
 * @author 袁臻
 * 2017/08/25 08:58
 */
@Mapper
public interface OrderMapper {
    @SelectProvider(type = OrderSqlProvider.class, method = "selectById")
    Order select(String id);

    @Select(value = "SELECT id, user_id, created_at FROM orders WHERE user_id = #{userId}")
    List<Order> selectByUserId(String userId);

    @InsertProvider(type = OrderSqlProvider.class, method = "insertSelective")
    int insert(Order order);

    @DeleteProvider(type = OrderSqlProvider.class, method = "deleteById")
    int delete(String id);
}
