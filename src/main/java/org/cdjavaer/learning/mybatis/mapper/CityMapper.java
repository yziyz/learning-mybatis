package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
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
    @SelectProvider(type = CitySqlProvider.class, method = "findAll")
    List<City> findAll(IndexCityDto dto);

    @SelectProvider(type = CitySqlProvider.class, method = "selectById")
    City find(Integer id);
}
