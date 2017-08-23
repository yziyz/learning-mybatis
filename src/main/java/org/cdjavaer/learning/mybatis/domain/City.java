package org.cdjavaer.learning.mybatis.domain;

/**
 * 城市实体类
 *
 * @author 袁臻
 * 2017/08/23 13:18
 */
public class City {
    private Integer code;
    private String name;

    public City(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
