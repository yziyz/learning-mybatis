package org.cdjavaer.learning.mybatis.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置类
 *
 * @author 袁臻
 * 2017/08/23 14:46
 */
@Configuration
@MapperScan(basePackages = "org.cdjavaer.learning.mybatis.mapper")
public class MybatisConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource(
                dataSourceProperties.getDriverClassName(),
                dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword()
        );
        dataSource.setPoolPingEnabled(true);
        dataSource.setPoolPingQuery("SELECT 1");
        return dataSource;
    }

    public SqlSessionFactory sqlSessionFactory() throws Exception {
        System.err.println("org.cdjavaer.learning.mybatis.config.MybatisConfig.sqlSessionFactory");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("org.cdjavaer.learning.mybatis.domain");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory();
    }

}
