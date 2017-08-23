package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.cdjavaer.learning.mybatis.domain.City;

import java.util.List;

/**
 * 城市映射类
 *
 * @author 袁臻
 * 2017/08/23 13:47
 */
@Mapper
public interface CityMapper {
    @Results(value = {
            @Result(id = true, property = "code", column = "code", javaType = Integer.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.INTEGER)
    })

    @Select("SELECT code, name FROM cities")
    List<City> findAll();

    @Select("SELECT code, name FROM cities WHERE code = #{code} LIMIT 1")
    City find(@Param("code") Integer code);
}
