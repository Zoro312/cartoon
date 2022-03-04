package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * cartoon_chapter
 * @author 
 */
@Data
public class CartoonChapter implements Serializable {
    private Integer id;

    private Integer cartoonid;

    private Integer chapterid;

    private String chapterName;

    private String chapterPath;

    private Integer limit;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartoonid() {
        return cartoonid;
    }

    public void setCartoonid(Integer cartoonid) {
        this.cartoonid = cartoonid;
    }

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterPath() {
        return chapterPath;
    }

    public void setChapterPath(String chapterPath) {
        this.chapterPath = chapterPath;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}