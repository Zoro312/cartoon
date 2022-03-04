package com.scartoon.starcartoon.service.impl;

import com.scartoon.starcartoon.dao.CartoonCommentDao;
import com.scartoon.starcartoon.dao.CommentLikeDao;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.CartoonComment;
import com.scartoon.starcartoon.pojo.entity.CommentLike;
import com.scartoon.starcartoon.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CartoonCommentDao cartoonCommentDao;
    @Resource
    CommentLikeDao commentLikeDao;

    /**
     * 添加评论
     */
    @Override
    public Map<String, Object> addComment(CartoonComment cartoonComment){
        Map<String, Object> resMap = new HashMap<>();
        int i = cartoonCommentDao.insertSelective(cartoonComment);
        if(i==1){//表示增加用户成功
            resMap.put("res",true);//增加成功
            resMap.put("msg","添加成功");//添加用户成功
        }else{
            resMap.put("res",false);//增加失败
            resMap.put("msg","添加失败");//添加用户失败
        }
        return resMap;
    }
    /**
     * 后台查看评论
     * @return
     */
    @Override
    public List<CartoonComment> findCommentByState(String state) {

        return this.cartoonCommentDao.selectCommentByState(state);
    }
    /**
     * 前台查看已通过评论
     * @return
     */
    @Override
    public List<CartoonComment> findCommentPassed() {

        return this.cartoonCommentDao.selectCommentPassed();
    }

    /**
     * 通过id得到评论信息
     * @param id
     * @return
     */
    @Override
    public CartoonComment getCommentById(Integer id) {

        return cartoonCommentDao.selectByPrimaryKey(id);
    }

    /**
     * 修改评论信息
     * @param cartoonComment
     * @return
     */
    @Override
    public Integer updateComment(CartoonComment cartoonComment) {
        return cartoonCommentDao.updateByPrimaryKeySelective(cartoonComment);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @Override
    public int deleteId(Integer id) {
        return cartoonCommentDao.deleteByPrimaryKey(id);
    }


    /**
     * 点赞表加数据
     * @param commentLike
     * @return
     */
    @Override
    public int addCommentLikeDate(CommentLike commentLike) {
        CommentLike dbCommentLike = commentLikeDao.selectCommentLikeById(commentLike.getCommentid(),commentLike.getUserid());
        if(dbCommentLike==null){
            commentLikeDao.insert(commentLike);
            commentLikeDao.updateByCommentId(commentLike.getCommentid());
        }
        return  0;
    }

    /**
     * 通过用户查点赞信息
     * @param userid
     * @return
     */
    @Override
    public List<CommentLike> findCommentLikeByUser(int userid) {

        return commentLikeDao.selectByUserId(userid);
    }
    /**
     * 取消点赞表删除数据
     * @param commentLike
     * @return
     */
    @Override
    public int deleteCommentLikeDate(CommentLike commentLike) {
        CommentLike dbCommentLike = commentLikeDao.selectCommentLikeById(commentLike.getCommentid(),commentLike.getUserid());
        if(dbCommentLike!=null){
            commentLikeDao.delete(commentLike.getCommentid(),commentLike.getUserid());
            commentLikeDao.updateByCommentId(commentLike.getCommentid());
        }
        return 0;
    }

    /**
     * 通过漫画id得到已通过漫画评论
     * @param id
     * @return
     */
    @Override
    public List<CartoonComment> getPassedCommentByCartoonId(Integer id) {

        return cartoonCommentDao.selectPassedCommentByCartoonId(id);
    }

}
