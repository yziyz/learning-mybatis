package org.cdjavaer.learning.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.interceptor.StatementCache;

/**
 * Jdbc拦截器，继承org.apache.tomcat.jdbc.pool.interceptor.StatementCache
 *
 * @author 袁臻
 * 2017/09/13 19:18
 */
@Slf4j
public class MyJdbcInterceptor extends StatementCache {

    /**
     * 重写cacheStatement方法，打印statementProxy
     */
    @Override
    public boolean cacheStatement(CachedStatement proxy) {
        log.info(proxy.getDelegate().toString());
        return super.cacheStatement(proxy);
    }
}
