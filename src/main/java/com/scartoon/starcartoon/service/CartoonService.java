package com.scartoon.starcartoon.service;

import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.CartoonChapter;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.pojo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 关于漫画的一些业务操作
 */
public interface CartoonService {

    /**
     * 添加用户的功能，返回res结果true或者false，返回msg为消息
     */
    Map<String,Object> addCartoon(CartoonTable cartoon);

    /**
     * 查看全部漫画详情
     * @return
     */
    List<CartoonTable> findCartoonAll();
    /**
     * 漫画管理中漫画详情
     * @return
     */
    List<CartoonTable> findCartoonByState(String state);
    /**
     * 通过作者查看漫画详情
     * @return
     */
    List<CartoonTable> findCartoonByAuthor(String authorname);

    /**
     * 通过id得到漫画信息
     * @param id
     * @return
     */
    CartoonTable getCartoonTableById(Integer id);
    /**
     * 通过id得到漫画的章节信息
     */
    List<CartoonChapter> getCartoonChapterById(Integer id);

    /**
     * 通过id得到已通过的漫画章节信息
     * @param id
     * @return
     */
    List<CartoonChapter> getPassedCartoonChapterById(Integer id);

    /**
     * 通过漫画和章节的id得到漫画章节信息
     * @param cartoonid
     * @param chapterid
     * @return
     */
    List<CartoonChapter> getCartoonChapterByDoubleId(Integer cartoonid, Integer chapterid);

    /**
     * 修改漫画详情信息
     * @param cartoon
     * @return
     */
    Integer updateCartoonTable(CartoonTable cartoon);

    /**
     * 修改漫画章节信息
     * @param chapter
     * @return
     */
    Integer updateCartoonChapter(CartoonChapter chapter);


    /**
     * 删除漫画信息
     * @param id
     * @return
     */
    Integer deleteId(Integer id);


    /**
     * 添加漫画章节
     * @param cartoonChapter
     * @return
     */
    Map<String,Object> addCartoonChapter(CartoonChapter cartoonChapter);


    /**
     * 首页上更新最新的6个漫画信息
     * @return
     */
    List<CartoonTable> cartoonUpdateList();
    /**
     * 排行榜页面漫画信息
     * @return
     */
    List<CartoonTable> cartoonRankingList(String ranking);
    /**
     * 排行榜时间排行漫画信息
     * @return
     */
    List<CartoonTable> cartoonUpdateListAll();
    /**
     * 首页上评分前十的漫画信息
     * @return
     */

    List<CartoonTable> cartoonScoreList();
    /**
     * 首页漫画排行榜的评分榜数据
     * @return
     */
    List<CartoonTable> cartoonScoreNotice();
    /**
     * 首页漫画排行榜的评论榜数据
     * @return
     */
    List<CartoonTable> cartoonCommentList();
    /**
     * 排行榜评论漫画详情
     * @return
     */
    List<CartoonTable> cartoonCommentListAll();


    /**
     *通过搜索得到漫画信息
     * @param keyW
     * @return
     */
    List<CartoonTable> getCartoonByKey(String keyW);

    /**
     * 分类得到漫画信息
     * @param cartoonRegion
     * @param cartoonTitle
     * @return
     */
    List<CartoonTable> findCartoonByTitle(String cartoonRegion, String cartoonTitle);

    List<CartoonTable> findCartoonByRe(String cartoonRegion);
    List<CartoonTable> findCartoonByTi(String cartoonTitle);


    /**
     * 通过作者查询他上传漫画的总数
     * @param authorname
     * @return
     */
    int findCartoonSumByAuthor(String authorname);
    /**
     * 通过作者查询他上传漫画通过的总数
     * @param authorname
     * @return
     */
    int findPassCartoonSumByAuthor(String authorname);
    /**
     * 通过作者查询他上传漫画未通过的总数
     * @param authorname
     * @return
     */
    int findNotPassCartoonSumByAuthor(String authorname);

    /**
     * 漫画总量
     * @return
     */
    int getCartoonSum();

    /**
     * 待审漫画总量
     * @return
     */
    int getNotPassCartoonSum();

    /**
     * 国漫总数
     * @return
     */
    int getGuoManSum();

    /**
     * 日漫总数
     * @return
     */
    int getRiManSum();



}
