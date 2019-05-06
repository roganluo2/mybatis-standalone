package com.gperedu.mybatis.entity;

/**
 * @Description TODO
 * @Date 2019/5/6 11:17
 * @Created by rogan.luo
 */
public class Comment {
    private Integer id;

    private String content;

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
}
