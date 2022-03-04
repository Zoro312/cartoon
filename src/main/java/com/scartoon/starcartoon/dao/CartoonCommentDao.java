package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.CartoonComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CartoonCommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CartoonComment record);

    int insertSelective(CartoonComment record);

    CartoonComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartoonComment record);

    int updateByPrimaryKey(CartoonComment record);
    /**
     * 后台查看评论
     */
    List<CartoonComment> selectCommentByState(String state);

    /**
     * 前台已通过评论
     * @return
     */
    List<CartoonComment> selectCommentPassed();

    /**
     * 通过漫画id得到已通过漫画评论
     * @param id
     * @return
     */
    List<CartoonComment> selectPassedCommentByCartoonId(Integer id);


}