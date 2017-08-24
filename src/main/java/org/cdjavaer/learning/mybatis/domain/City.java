package org.cdjavaer.learning.mybatis.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市实体类
 *
 * @author 袁臻
 * 2017/08/23 13:18
 */
@Table(name = "cities")
public class City {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
