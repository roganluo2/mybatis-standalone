package com.gperedu.mybatis.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * @Description
 * @Date 2019/5/9 11:35
 * @Created by rogan.luo
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class MyPageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter =  args[1];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql();
        RowBounds rowBounds = (RowBounds) args[2];
        //这里不用分页
        if(rowBounds == null)
        {
            return invocation.proceed();
        }
        sql = sql + String.format(" limit %s, %s", rowBounds.getOffset(), rowBounds.getLimit());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(),sql,parameterMappings);
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms,sqlSource);
        Field offset = RowBounds.class.getDeclaredField("offset");
        offset.setAccessible(true);
        offset.set(rowBounds,0);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
