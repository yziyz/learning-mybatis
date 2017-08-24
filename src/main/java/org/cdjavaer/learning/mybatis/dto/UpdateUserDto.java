package org.cdjavaer.learning.mybatis.dto;

import java.time.LocalDate;

/**
 * 更新用户DTO
 *
 * @author 袁臻
 * 2017/08/24 17:30
 */
public class UpdateUserDto {
    private String id;
    private String name;
    private LocalDate birthDay;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
