package com.gperedu.mybatis.interceptor;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.List;
import java.util.Properties;

/**
 * @Description 日志
 * @Date 2019/5/9 9:12
 * @Created by rogan.luo
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class LoggingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter =  args[1];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql();
//        BoundSql boundSql = (BoundSql) args[5];
        sql = setParameter(ms, boundSql, sql);
        System.out.println( "[origin sql]:" + sql);
        Object proceed ;
        long startTime = System.currentTimeMillis();
        try {
            proceed = invocation.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println("统计SQL执行耗时：" + (endTime-startTime) +"ms");
        }
        return proceed;
    }

    private String setParameter(MappedStatement ms, BoundSql boundSql, String sql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = ms.getConfiguration().getTypeHandlerRegistry();
        for (ParameterMapping parameterMapping : parameterMappings) {
            if (parameterMapping.getMode() != ParameterMode.OUT) {
                Object value;
                String propertyName = parameterMapping.getProperty();
                if (boundSql.hasAdditionalParameter(propertyName)) {
                    value = boundSql.getAdditionalParameter(propertyName);
                } else if (parameterObject == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    MetaObject metaObject = ms.getConfiguration().newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
               sql =  sql.replace("?", JSONObject.toJSONString(value));
            }
        }
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
