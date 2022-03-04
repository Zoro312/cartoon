package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.CartoonChapter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CartoonChapterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CartoonChapter record);

    int insertSelective(CartoonChapter record);

    CartoonChapter selectByPrimaryKey(Integer id);

    //根据漫画id查询漫画章节信息
    List<CartoonChapter> selectCartoonChapterById(Integer cartoonid);
    //通过id得到已通过的漫画章节信息
    List<CartoonChapter> selectPassedCartoonChapterById(Integer id);
    //根据漫画和章节的id查询漫画章节信息
    List<CartoonChapter> selectCartoonChapterByDoubleId(Integer cartoonid, Integer chapterid);
    //根据漫画和章节的id查询是否存在
    CartoonChapter selectChapterById(Integer cartoonid, Integer chapterid);
    int updateByPrimaryKeySelective(CartoonChapter record);

    int updateByPrimaryKey(CartoonChapter record);



}