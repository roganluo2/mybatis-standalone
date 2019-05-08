package com.gperedu.mybatis.mapper;

import com.gperedu.mybatis.BaseTest;
import com.gperedu.mybatis.entity.Blog;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 批处理测试
 * @Date 2019/5/7 17:27
 * @Created by rogan.luo
 */
public class BatchTest extends BaseTest {

    int total = 1_000;

    @Test
    public void testBatchSQL(){
        SqlSession sqlSession = getSqlSession(true);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        List<Blog> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0 ; i < total; i++)
        {
            list.add(new Blog(null,"sql_insert" + i ));
            if(list.size() >= 500)
            {
                mapper.batchInsert(list);
                list.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("入库"+total+"条数据，耗时：" + (end-start)+"ms");

    }

    @Test
    public void testBatchExcutor(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
        long start = System.currentTimeMillis();
        for(int i = 0 ; i < total; i++)
        {
            mapper.insert(new Blog(null,"batch_insert" + i));
        }
        sqlSession.commit();
        long end = System.currentTimeMillis();
        System.out.println("入库"+total+"条数据，耗时：" + (end-start)+"ms");
    }

}
