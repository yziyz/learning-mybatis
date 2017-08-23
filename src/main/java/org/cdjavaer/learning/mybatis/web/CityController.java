package org.cdjavaer.learning.mybatis.web;

import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.dto.IndexCityDto;
import org.cdjavaer.learning.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * city控制器
 *
 * @author 袁臻
 * 2017/08/23 16:14
 */
@RestController
@RequestMapping(value = "city")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "{code}")
    public City find(@PathVariable("code") Integer code) {
        return cityService.find(code);
    }

    @GetMapping
    public List<City> findAll(@ModelAttribute IndexCityDto dto) {
        return cityService.findAll(dto);
    }
}
