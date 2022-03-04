package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * cartoon_type
 * @author 
 */
@Data
public class CartoonType implements Serializable {
    private Integer typeid;

    /**
     * 类别
     */
    private String category;

    /**
     * 标题
     */
    private String title;

    private static final long serialVersionUID = 1L;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}