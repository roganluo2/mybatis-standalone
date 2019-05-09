package com.gperedu.mybatis.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gperedu.mybatis.BaseTest;
import com.gperedu.mybatis.entity.Blog;
import com.gperedu.mybatis.mapper.BolgMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Description TODO
 * @Date 2019/5/9 11:27
 * @Created by rogan.luo
 */
public class LoggingInterceptorTest extends BaseTest {

    @Test
    public void testQuery(){
        SqlSession sqlSession = getSqlSession(false);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        Blog query = new Blog(3,"SQL");
        Blog blog = mapper.selectBySelective(query);
        System.out.println("返回结果：" + JSONObject.toJSONString(blog));
    }
}
