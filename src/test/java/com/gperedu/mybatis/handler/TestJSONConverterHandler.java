package com.gperedu.mybatis.handler;

import com.gperedu.mybatis.BaseTest;
import com.gperedu.mybatis.entity.Blog;
import com.gperedu.mybatis.entity.Comment;
import com.gperedu.mybatis.mapper.BolgMapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description TODO
 * @Date 2019/5/6 12:43
 * @Created by rogan.luo
 */
public class TestJSONConverterHandler extends BaseTest {


    @Test
    public void testAddBlog(){
        Blog blog = new Blog();
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("test");
        blog.setComment(comment);
        blog.setContent("contet");
        SqlSession sqlSession = getSqlSession(true);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        int i = mapper.insertSelective(blog);
        Assert.assertTrue(i > 0);
    }


    @Test
    public void testGetBlog(){
        SqlSession sqlSession = getSqlSession(false);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        Blog blog = mapper.selectByPrimaryKey(3);
        sqlSession.commit();
        //开启第二个session
        SqlSession sqlSession1 = getSqlSession(false);
        BolgMapper mapper1 = sqlSession1.getMapper(BolgMapper.class);
        System.out.println("第二次查询同样的值");
        blog = mapper1.selectByPrimaryKey(3);
//        Assert.assertNotNull(blog.getComment());
        System.out.println(blog.getComment().getContent());
    }

}
