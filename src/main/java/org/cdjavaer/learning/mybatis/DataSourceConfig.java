package org.cdjavaer.learning.mybatis;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置数据源
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataSourceConfig {
    private final @NonNull
    DataSourceProperties dataSourceProperties;

    @Value("${spring.datasource.jdbc-interceptors}")
    private String jdbcInterceptor;

    @Bean
    protected DataSource dataSource() {
        PoolProperties p = new PoolProperties();
        p.setUrl(dataSourceProperties.getUrl());
        p.setDriverClassName(dataSourceProperties.getDriverClassName());
        p.setUsername(dataSourceProperties.getUsername());
        p.setPassword(dataSourceProperties.getPassword());
        p.setJdbcInterceptors(jdbcInterceptor);

        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(p);

        return dataSource;
    }
}