package org.cdjavaer.learning.mybatis.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 订单实体类
 *
 * @author 袁臻
 * 2017/08/24 15:15
 */
@Table(name = "orders")
public class Order {
    /**
     * 订单ID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
