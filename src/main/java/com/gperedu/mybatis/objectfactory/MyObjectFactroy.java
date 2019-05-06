package com.gperedu.mybatis.objectfactory;

import com.gperedu.mybatis.entity.Blog;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;

/**
 * @Description
 * @Date 2019/5/6 16:34
 * @Created by rogan.luo
 */
public class MyObjectFactroy extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);
        if(Blog.class.isAssignableFrom(type))
        {
            Blog blog = (Blog) t;
            blog.setContent(blog.getContent() + "-- 我要变得更加更好看");
        }
        return t;
    }
}
