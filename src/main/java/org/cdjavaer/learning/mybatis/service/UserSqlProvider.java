package org.cdjavaer.learning.mybatis.service;

import cn.com.zdht.pavilion.mybatis.BaseSqlProvider;
import org.apache.ibatis.jdbc.SQL;
import org.cdjavaer.learning.mybatis.domain.User;
import org.cdjavaer.learning.mybatis.dto.UpdateUserDto;

/**
 * 用户SQL提供类
 *
 * @author 袁臻
 * 2017/08/24 09:31
 */
public class UserSqlProvider extends BaseSqlProvider<User, String> {
    public String updateDto(UpdateUserDto dto) {
        SQL sql = new SQL(){
            {
                UPDATE("users");
                if (dto.getName() != null) {
                    SET("name = #{name}");
                }
                if (dto.getBirthDay() != null) {
                    SET("birth_day = #{birthDay}");
                }
                if (dto.getCityId() != null) {
                    SET("city_id = #{cityId}");
                }
                WHERE("id = #{id}");
            }
        };
        return sql.toString();
    }
}
