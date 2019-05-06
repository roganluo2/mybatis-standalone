package com.gperedu.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description TODO
 * @Date 2019/5/6 12:44
 * @Created by rogan.luo
 */
public class BaseTest {

    public SqlSessionFactory sqlSessionFactory ;
    private String configPath = "mybatis-cfg.xml";


    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession getSqlSession(boolean flag){
        return sqlSessionFactory.openSession(flag);
    }

}
