package org.cdjavaer.learning.mybatis.service;

import com.github.pagehelper.PageHelper;
import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.dto.IndexCityDto;
import org.cdjavaer.learning.mybatis.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 袁臻
 * 2017/08/23 14:58
 */
@Service
@Transactional
public class CityService {
    /**
     * 城市映射实例
     */
    private CityMapper cityMapper;

    @Autowired
    protected CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    /**
     * 返回根据指定ID对应的城市实例
     *
     * @param id 指定ID
     * @return 对应的城市实例
     */
    public City select(Integer id) {
        return cityMapper.select(id);
    }

    /**
     * 将指定的城市实例写入数据库中并返回
     *
     * @param city 指定的城市实例
     * @return 指定的城市实例
     */
    public City insert(City city) {
        cityMapper.insert(city);
        return city;
    }

    /**
     * 根据指定的查询城市DTO实例返回匹配的城市列表
     * @param dto 指定的查询城市DTO实例
     * @return 匹配的城市列表
     */
    public List<City> selectByDto(IndexCityDto dto) {
        if (dto.getPage() != null && dto.getSize() != null) {
            PageHelper.startPage(dto.getPage(), dto.getSize());
        }
        return cityMapper.selectByDto(dto);
    }
}
