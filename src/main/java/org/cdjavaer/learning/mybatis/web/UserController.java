package org.cdjavaer.learning.mybatis.web;

import cn.com.zdht.pavilion.message.dosser.CommonException;
import org.cdjavaer.learning.mybatis.domain.User;
import org.cdjavaer.learning.mybatis.dto.CreateUserDto;
import org.cdjavaer.learning.mybatis.dto.UpdateUserDto;
import org.cdjavaer.learning.mybatis.service.CityService;
import org.cdjavaer.learning.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 用户控制器
 *
 * @author 袁臻
 * 2017/08/24 10:01
 */
@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    /**
     * 服务实例
     */
    private UserService userService;
    private CityService cityService;

    /**
     * 构造方法
     * 由Spring执行并注入实例
     *
     * @param userService 用户服务实例
     * @param cityService 城市服务实例
     */
    @Autowired
    protected UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping(value = "{id}")
    public User select(@PathVariable("id") String id) {
        return userService.select(id);
    }

    @PostMapping
    public User create(@RequestBody CreateUserDto dto) {
        String id = UUID.randomUUID().toString();
        dto.setId(id);

        //若dto中的城市不存在，则抛出异常
        if (cityService.select(dto.getCityId()) == null) {
            throw CommonException.badRequests("不存在该城市");
        }
        //持久化
        userService.insert(dto);

        //返回创建的用户实例
        return userService.select(id);
    }

    @PutMapping
    public User update(@RequestBody UpdateUserDto dto) {
        //若dto中id为空。抛出异常
        if (dto.getId() == null) {
            throw CommonException.badRequests("用户ID不能为空");
        }
        //若dto中的城市不存在，则抛出异常
        if (cityService.select(dto.getCityId()) == null) {
            throw CommonException.badRequests("不存在该城市");
        }
        //持久化
        userService.update(dto);
        //返回
        return userService.select(dto.getId());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
