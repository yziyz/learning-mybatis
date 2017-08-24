package org.cdjavaer.learning.mybatis.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 创建用户DTO
 *
 * @author 袁臻
 * 2017/08/24 10:11
 */
public class CreateUserDto {
    /**
     * ID
     */
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
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * 所在城市ID
     */
    private Integer cityId;

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
