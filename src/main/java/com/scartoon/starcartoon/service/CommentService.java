package com.scartoon.starcartoon.service;

import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.CartoonComment;
import com.scartoon.starcartoon.pojo.entity.CommentLike;

import java.util.List;
import java.util.Map;

public interface CommentService {

    /**
     * 添加评论
     */
    Map<String, Object> addComment(CartoonComment cartoonComment);
    /**
     * 后台查看评论
     * @return
     */
    List<CartoonComment> findCommentByState(String state);
    /**
     * 前台已通过评论
     * @return
     */
    List<CartoonComment> findCommentPassed();

    /**
     * 通过id得到评论
     * @param id
     * @return
     */
    CartoonComment getCommentById(Integer id);

    /**
     * 修改评论信息
     * @param cartoonComment
     * @return
     */
    Integer updateComment(CartoonComment cartoonComment);

    /**
     * 删除评论
     * @param id
     */
    int deleteId(Integer id);

    /**
     * 点赞表中加数据
     * @param commentLike
     * @return
     */
    int addCommentLikeDate(CommentLike commentLike);

    /**
     * 通过用户查点赞数据
     * @param userid
     * @return
     */
    List<CommentLike> findCommentLikeByUser(int userid);
    /**
     * 取消点赞表中删除数据
     * @param commentLike
     * @return
     */
    int deleteCommentLikeDate(CommentLike commentLike);

    /**
     *详情页该漫画已通过的评论
     * @param id
     * @return
     */
    List<CartoonComment> getPassedCommentByCartoonId(Integer id);

}
