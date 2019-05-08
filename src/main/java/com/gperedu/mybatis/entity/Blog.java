package com.gperedu.mybatis.entity;

import java.io.Serializable;

/**
 * @Description TODO
 * @Date 2019/5/6 11:16
 * @Created by rogan.luo
 */
public class Blog implements Serializable {

    private Integer id;

    private String content;

    private Comment comment;

    public Blog(){}

    public Blog(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
