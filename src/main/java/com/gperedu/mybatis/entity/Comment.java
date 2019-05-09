package com.gperedu.mybatis.entity;

import java.io.Serializable;

/**
 * @Description TODO
 * @Date 2019/5/6 11:17
 * @Created by rogan.luo
 */
public class Comment implements Serializable {
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
