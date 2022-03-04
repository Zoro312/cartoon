package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CommentLikeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    CommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);

    /**
     * 根据id查询这个漫画点赞的基本信息
     * @param commentid
     * @param userid
     * @return
     */
    CommentLike selectCommentLikeById(Integer commentid, Integer userid);

    /**
     * 通过用户查点赞信息
     * @param userid
     * @return
     */
    List<CommentLike> selectByUserId(int userid);

    /**
     * 删除数据
     * @param commentid
     * @param userid
     */
    void delete(Integer commentid, Integer userid);

    /**
     * 更新点赞数量
     * @param commentid
     */
    void updateByCommentId(Integer commentid);
}