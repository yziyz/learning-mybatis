package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.dto.IndexCityDto;
import org.cdjavaer.learning.mybatis.service.CitySqlProvider;

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
            @Result(id = true, property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.INTEGER)
    })

    @SelectProvider(type = CitySqlProvider.class, method = "findAll")
    List<City> findAll(IndexCityDto dto);

    @SelectProvider(type = CitySqlProvider.class, method = "selectById")
    City find(Integer code);
}
