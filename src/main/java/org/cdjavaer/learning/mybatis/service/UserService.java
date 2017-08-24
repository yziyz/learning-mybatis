package org.cdjavaer.learning.mybatis.service;

import org.cdjavaer.learning.mybatis.domain.User;
import org.cdjavaer.learning.mybatis.dto.CreateUserDto;
import org.cdjavaer.learning.mybatis.dto.UpdateUserDto;
import org.cdjavaer.learning.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务类
 *
 * @author 袁臻
 * 2017/08/24 09:44
 */
@Service
@Transactional
public class UserService {
    /**
     * 用户映射实例
     */
    private UserMapper userMapper;

    /**
     * 构造方法
     * 由Spring执行并注入实例
     *
     * @param userMapper 用户映射实例
     */
    @Autowired
    protected UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User select(String id) {
        return userMapper.select(id);
    }

    public int insert(CreateUserDto dto) {
        return userMapper.insert(dto);
    }

    public int update(UpdateUserDto dto) {
        return userMapper.update(dto);
    }

    public int delete(String id) {
        return userMapper.delete(id);
    }
}
