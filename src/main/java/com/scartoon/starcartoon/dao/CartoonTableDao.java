package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CartoonTableDao {
    int deleteByPrimaryKey(Integer cartoonid);

    int insert(CartoonTable record);

    int insertSelective(CartoonTable record);

    CartoonTable selectByPrimaryKey(Integer cartoonid);

    int updateByPrimaryKeySelective(CartoonTable record);

    int updateByPrimaryKey(CartoonTable record);

    /**
     * 查看全部漫画详情
     * @return
     */
    List<CartoonTable> selectCartoonAll();

    /**
     * 漫画管理中的漫画详情
     * @param state
     * @return
     */
    List<CartoonTable> selectCartoonByState(String state);
    /**
     * 通过作者查看漫画详情
     * @return
     */
    List<CartoonTable> selectCartoonByAuthor(String authorname);
    /**
     * 首页更新前6的漫画
     * @return
     */
    List<CartoonTable> selectCartoonUpdate();
    /**
     * 排行榜页面漫画
     * @return
     */
    List<CartoonTable> selectCartoonRankingAll(String ranking);
    /**
     * 排行榜时间排行漫画
     * @return
     */
    List<CartoonTable> selectCartoonUpdateAll();
    /**
     * 首页排名前十的漫画
     * @return
     */
    List<CartoonTable> selectCartoonTen();

    /**
     * 首页漫画排行榜的评分榜数据
     * @return
     */
    List<CartoonTable> selectCartoonNotice();

    /**
     * 首页漫画排行榜的评论榜数据
     * @return
     */
    List<CartoonTable> selectCartoonComment();

    /**
     * 排行榜评论漫画详情
     * @return
     */
    List<CartoonTable> selectCartoonCommentAll();

    /**
     * 通过搜索得到漫画信息
     * @param keyW
     * @return
     */
    List<CartoonTable> selectCartoonByKey(String keyW);

    /**
     * 通过分类得到漫画信息
     * @param cartoonRegion
     * @param cartoonTitle
     * @return
     */
    List<CartoonTable> selectCartoonByTitle(String cartoonRegion, String cartoonTitle);

    List<CartoonTable> selectCartoonByRe(String cartoonRegion);
    List<CartoonTable> selectCartoonByTi(String cartoonTitle);

    /**
     * 通过作者查询他上传漫画的总数
     * @param authorname
     * @return
     */
    int selectCartoonSumByAuthor(String authorname);

    /**
     * 通过作者查询他上传漫画通过的总数
     * @param authorname
     * @return
     */
    int selectPassCartoonSumByAuthor(String authorname);

    /**
     * 通过作者查询他上传漫画未通过的总数
     * @param authorname
     * @return
     */
    int selectNotPassCartoonSumByAuthor(String authorname);

    /**
     * 漫画总量
     * @return
     */
    int selectCartoonSum();

    /**
     * 待审漫画总量
     * @return
     */
    int selectNotPassCartoonSum();

    /**
     * 国漫总数
     * @return
     */
    int selectGuoManSum();

    /**
     * 日漫总数
     * @return
     */
    int selectRiManSum();


}