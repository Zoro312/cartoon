package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * comment_like
 * @author 
 */
@Data
public class CommentLike implements Serializable {
    private Integer id;

    /**
     * 点赞人ID
     */
    private Integer userid;

    /**
     * 评论ID
     */
    private Integer commentid;

    /**
     * 点赞时间
     */
    private Date timestamp;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}