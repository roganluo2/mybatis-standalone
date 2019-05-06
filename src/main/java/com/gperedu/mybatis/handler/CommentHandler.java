package com.gperedu.mybatis.handler;

import com.alibaba.fastjson.JSONObject;
import com.gperedu.mybatis.entity.Comment;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 只是对comment类型进行json转换
 * @Date 2019/5/6 12:53
 * @Created by rogan.luo
 */
public class CommentHandler extends BaseTypeHandler<Comment> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Comment comment, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONObject.toJSONString(comment));
    }

    @Override
    public Comment getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSONObject.parseObject(resultSet.getString(s),Comment.class);
    }

    @Override
    public Comment getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONObject.parseObject(resultSet.getString(i),Comment.class);
    }

    @Override
    public Comment getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
