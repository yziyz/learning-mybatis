package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.*;
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
    @SelectProvider(type = CitySqlProvider.class, method = "selectByDto")
    List<City> selectByDto(IndexCityDto dto);

    //@Select(value = "SELECT id, name FROM cities WHERE id = #{id}")
    @SelectProvider(type = CitySqlProvider.class, method = "selectById")
    City select(Integer id);

    @InsertProvider(type = CitySqlProvider.class, method = "insertSelective")
    int insert(City city);

    @UpdateProvider(type = CitySqlProvider.class, method = "updateSelective")
    int update(City city);

    @DeleteProvider(type = CitySqlProvider.class, method = "deleteById")
    int delete(String id);
}
