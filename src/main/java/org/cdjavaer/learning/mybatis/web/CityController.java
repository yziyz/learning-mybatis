package org.cdjavaer.learning.mybatis.web;

import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.dto.IndexCityDto;
import org.cdjavaer.learning.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * city控制器
 *
 * @author 袁臻
 * 2017/08/23 16:14
 */
@RestController
@RequestMapping(value = "cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
    /**
     * 城市服务实例
     */
    private CityService cityService;

    /**
     * 构造方法
     * 由Spring执行并注入实例
     *
     * @param cityService 城市服务实例
     */
    @Autowired
    protected CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "{id}")
    public City select(@PathVariable("id") Integer code) {
        return cityService.select(code);
    }

    @GetMapping
    public List<City> selectAll(@ModelAttribute IndexCityDto dto) {
        return cityService.selectByDto(dto);
    }
}
