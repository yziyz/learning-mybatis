package org.cdjavaer.learning.mybatis.web;

import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<City> findAll() {
        return cityService.findAll();
    }
}
