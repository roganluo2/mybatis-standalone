package com.gperedu.mybatis.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gperedu.mybatis.BaseTest;
import com.gperedu.mybatis.entity.Blog;
import com.gperedu.mybatis.mapper.BolgMapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/5/9 12:37
 * @Created by rogan.luo
 */
public class MyPageInterceptorTest extends BaseTest {

    @Test
    public void testPage(){
        RowBounds rowBounds = new RowBounds(5,5);
        SqlSession sqlSession = getSqlSession(false);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        List<Blog> list = mapper.selectBlogList(rowBounds);
        System.out.println("返回结果：" + JSONObject.toJSONString(list));
    }

}
