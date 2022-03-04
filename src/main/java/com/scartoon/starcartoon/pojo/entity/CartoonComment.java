package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cartoon_comment
 * @author 
 */
@Data
public class CartoonComment implements Serializable {
    private Integer id;

    /**
     * 漫画id
     */
    private Integer cartoonid;

    /**
     * 用户
     */
    private String username;

    /**
     * 评论
     */
    private String comment;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 点赞数
     */
    private Integer likesum;

    /**
     * 时间戳
     */
    private Date timestamp;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLikesum() {
        return likesum;
    }

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}