package org.cdjavaer.learning.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.domain.User;
import org.cdjavaer.learning.mybatis.dto.CreateUserDto;
import org.cdjavaer.learning.mybatis.dto.UpdateUserDto;
import org.cdjavaer.learning.mybatis.service.UserSqlProvider;

/**
 * 用户映射类
 *
 * @author 袁臻
 * 2017/08/24 09:08
 */
@Mapper
public interface UserMapper {
    @Results(value = {
            @Result(property = "city", column = "city_id",
                    javaType = City.class, jdbcType = JdbcType.INTEGER,
                    one = @One(select = "org.cdjavaer.learning.mybatis.mapper.CityMapper.select",
                            fetchType = FetchType.EAGER)),
            @Result(property = "orders", column = "id",
                    many = @Many(select = "org.cdjavaer.learning.mybatis.mapper.OrderMapper.selectByUserId",
                            fetchType = FetchType.LAZY))
    })
    @SelectProvider(type = UserSqlProvider.class, method = "selectById")
    User select(String id);

    @Insert(value = "INSERT INTO users(id, name, birth_day, created_at, city_id) VALUES(#{id}, #{name}, #{birthDay}, #{createdAt}, #{cityId})")
    int insert(CreateUserDto dto);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateDto")
    int update(UpdateUserDto dto);

    @DeleteProvider(type = UserSqlProvider.class, method = "deleteById")
    int delete(String id);
}
