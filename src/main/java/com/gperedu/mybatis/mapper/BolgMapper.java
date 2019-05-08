package com.gperedu.mybatis.mapper;

import com.gperedu.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/5/6 11:17
 * @Created by rogan.luo
 */
public interface BolgMapper {

    int insertSelective(Blog blog);

    int insert(Blog blog);

    Blog selectByPrimaryKey(@Param("id") Integer id);

    void batchInsert(@Param("blogs") List<Blog> blogs);

    @Update("update gper_blog set comment = #{comment} where id = #{id}")
    int updateById(Blog blog);

}
