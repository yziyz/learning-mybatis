package org.cdjavaer.learning.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.cdjavaer.learning.mybatis.domain.City;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 城市类型处理器
 *
 * @author 袁臻
 * 2017/08/24 16:14
 */
@MappedJdbcTypes(value = {JdbcType.INTEGER})
@MappedTypes(value = City.class)
public class CityTypeHandler extends BaseTypeHandler<City> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, City parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public City getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public City getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public City getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
