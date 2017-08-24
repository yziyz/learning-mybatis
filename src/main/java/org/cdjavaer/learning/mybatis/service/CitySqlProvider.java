package org.cdjavaer.learning.mybatis.service;

import cn.com.zdht.pavilion.mybatis.BaseSqlProvider;
import org.apache.ibatis.jdbc.SQL;
import org.cdjavaer.learning.mybatis.domain.City;
import org.cdjavaer.learning.mybatis.dto.IndexCityDto;

/**
 * 城市SQL构造类
 *
 * @author 袁臻
 * 2017/08/23 17:28
 */
public class CitySqlProvider extends BaseSqlProvider<City, Integer> {
    /**
     * 返回根据指定的IndexCityDto实例生成的动态SQL
     *
     * @param dto 指定的IndexCityDto实例
     * @return 根据指定的IndexCityDto实例生成的动态SQL
     */
    public String selectAll(final IndexCityDto dto) {
        return new SQL() {
            {
                SELECT("id", "name");
                FROM("cities");
                if (dto.getName() != null) {
                    WHERE("name LIKE '%' || #{name} || '%'");
                }
                ORDER_BY("id");
            }
        }.toString();
    }
}
