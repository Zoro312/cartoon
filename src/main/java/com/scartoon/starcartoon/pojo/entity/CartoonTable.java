package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cartoon_table
 * @author
 */
@Data
public class CartoonTable implements Serializable {
    /**
     * 漫画主键
     */
    private Integer cartoonid;

    /**
     * 漫画名
     */
    private String cartoonname;

    /**
     * 漫画地区类型
     */
    private String regionType;

    /**
     * 漫画题材类型
     */
    private String titleType;

    /**
     * 漫画作者
     */
    private String cartoonAuthor;

    /**
     * 漫画简介
     */
    private String cartoonDesc;

    /**
     * 封面地址
     */
    private String imagepath;

    /**
     * 评分
     */
    private Double score;

    /**
     * 进度
     */
    private String state;

    /**
     * 限制
     */
    private String limit;

    /**
     * 时间戳
     */
    private Date timestamp;

    private static final long serialVersionUID = 1L;

    public Integer getCartoonid() {
        return cartoonid;
    }

    public void setCartoonid(Integer cartoonid) {
        this.cartoonid = cartoonid;
    }

    public String getCartoonname() {
        return cartoonname;
    }

    public void setCartoonname(String cartoonname) {
        this.cartoonname = cartoonname;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getCartoonAuthor() {
        return cartoonAuthor;
    }

    public void setCartoonAuthor(String cartoonAuthor) {
        this.cartoonAuthor = cartoonAuthor;
    }

    public String getCartoonDesc() {
        return cartoonDesc;
    }

    public void setCartoonDesc(String cartoonDesc) {
        this.cartoonDesc = cartoonDesc;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}