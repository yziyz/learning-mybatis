package org.cdjavaer.learning.mybatis.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体类
 *
 * @author 袁臻
 * 2017/08/24 09:02
 */
@Table(name = "users")
public class User {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 生日
     */
    private LocalDate birthDay;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 所在城市ID
     */
    private City city;

    /**
     * 订单
     */
    private List<Order> orders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
