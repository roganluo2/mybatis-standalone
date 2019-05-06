package com.gperedu.mybatis.mapper;

import com.gperedu.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Param;

/**
 * @Description TODO
 * @Date 2019/5/6 11:17
 * @Created by rogan.luo
 */
public interface BolgMapper {

    int insertSelective(Blog blog);

    Blog selectByPrimaryKey(@Param("id") Integer id);

}
