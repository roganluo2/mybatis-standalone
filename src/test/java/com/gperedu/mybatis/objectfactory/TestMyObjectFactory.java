package com.gperedu.mybatis.objectfactory;

import com.alibaba.fastjson.JSONObject;
import com.gperedu.mybatis.entity.Blog;
import org.junit.Test;

/**
 * @Description TODO
 * @Date 2019/5/6 16:54
 * @Created by rogan.luo
 */
public class TestMyObjectFactory {

    @Test
    public void testCteate(){
        MyObjectFactroy myObjectFactroy = new MyObjectFactroy();
        Blog blog = myObjectFactroy.create(Blog.class);
        System.out.println(JSONObject.toJSONString(blog));
    }

}
