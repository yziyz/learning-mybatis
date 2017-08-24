package org.cdjavaer.learning.mybatis.dto;

import java.time.LocalDate;

/**
 * 查询用户DTO
 *
 * @author 袁臻
 * 2017/08/24 09:53
 */
public class IndexUserDto {
    /**
     * 姓名关键词
     */
    private String name;

    /**
     * 生日起始日期
     */
    private LocalDate birthedFrom;

    /**
     * 生日结束日期
     */
    private LocalDate birthedTo;

    /**
     * 创建起始日期
     */
    private LocalDate createdFrom;

    /**
     * 创建结束日期
     */
    private LocalDate createdTo;

    /**
     * 城市名称关键词
     */
    private String cityName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthedFrom() {
        return birthedFrom;
    }

    public void setBirthedFrom(LocalDate birthedFrom) {
        this.birthedFrom = birthedFrom;
    }

    public LocalDate getBirthedTo() {
        return birthedTo;
    }

    public void setBirthedTo(LocalDate birthedTo) {
        this.birthedTo = birthedTo;
    }

    public LocalDate getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(LocalDate createdFrom) {
        this.createdFrom = createdFrom;
    }

    public LocalDate getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(LocalDate createdTo) {
        this.createdTo = createdTo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
