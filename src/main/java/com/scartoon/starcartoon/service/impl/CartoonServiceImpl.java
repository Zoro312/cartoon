package com.scartoon.starcartoon.service.impl;

import com.scartoon.starcartoon.dao.CartoonChapterDao;
import com.scartoon.starcartoon.dao.CartoonTableDao;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.CartoonChapter;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.service.CartoonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CartoonServie接口实现类
 */
@Service  //交给springIoc（容器管理）后边就可以直接装配只用它了
public class CartoonServiceImpl implements CartoonService {

    @Resource
    CartoonTableDao cartoonTableDao;

    @Resource
    CartoonChapterDao cartoonChapterDao;

    /**
     * 添加用户
     * @param cartoon
     * @return
     */
    @Override
    public Map<String, Object> addCartoon(CartoonTable cartoon) {

        Map<String, Object> resMap = new HashMap<>();
        int i = cartoonTableDao.insertSelective(cartoon);
        if(i==1){//表示增加用户成功
            resMap.put("res",true);//增加成功
            resMap.put("msg","添加漫画成功");//添加用户成功
        }else{
            resMap.put("res",false);//增加失败
            resMap.put("msg","添加漫画失败");//添加用户失败
        }
        return resMap;
    }

    /**
     * 查看全部漫画详情
     */
    @Override
    public List<CartoonTable> findCartoonAll(){

        return this.cartoonTableDao.selectCartoonAll();
    }

    /**
     * 漫画管理中的漫画详情
     * @param state
     * @return
     */
    @Override
    public List<CartoonTable> findCartoonByState(String state) {
        return this.cartoonTableDao.selectCartoonByState(state);
    }


    /**
     * 通过作者查看漫画详情
     */
    @Override
    public List<CartoonTable> findCartoonByAuthor(String authorname){

        return this.cartoonTableDao.selectCartoonByAuthor(authorname);
    }

    /**
     * 通过id得到漫画信息
     * @param id
     * @return
     */
    @Override
    public CartoonTable getCartoonTableById(Integer id) {

        return cartoonTableDao.selectByPrimaryKey(id);
    }
    /**
     * 通过id得到漫画章节信息
     */
    @Override
    public List<CartoonChapter> getCartoonChapterById(Integer id) {

        return this.cartoonChapterDao.selectCartoonChapterById(id);
    }

    /**
     * 通过id得到已通过的漫画章节信息
     * @param id
     * @return
     */
    @Override
    public List<CartoonChapter> getPassedCartoonChapterById(Integer id) {
        return this.cartoonChapterDao.selectPassedCartoonChapterById(id);
    }

    /**
     * 通过漫画和章节的id得到漫画章节信息
     * @param cartoonid
     * @param chapterid
     * @return
     */
    public List<CartoonChapter> getCartoonChapterByDoubleId(Integer cartoonid, Integer chapterid){
        return this.cartoonChapterDao.selectCartoonChapterByDoubleId(cartoonid,chapterid);
    }


    /**
     * 修改漫画详情信息
     * @param cartoon
     * @return
     */
    @Override
    public Integer updateCartoonTable(CartoonTable cartoon) {
        int res;
        int i = cartoonTableDao.updateByPrimaryKeySelective(cartoon);
        if(i==1){//表示修改漫画成功
            res=1;
        }else{
            res=0;
        }
        return res;
    }

    /**
     * 修改漫画章节信息
     * @param chapter
     * @return
     */
    @Override
    public Integer updateCartoonChapter(CartoonChapter chapter) {
        int res;
        int i = cartoonChapterDao.updateByPrimaryKeySelective(chapter);
        if(i==1){//表示修改漫画成功
            res=1;
        }else{
            res=0;
        }
        return res;
    }

    /**
     * 删除漫画
     * @param id
     * @return
     */
    @Override
    public Integer deleteId(Integer id) {
        return cartoonTableDao.deleteByPrimaryKey(id);
    }
    /**
     * 添加漫画章节
     * @param cartoonChapter
     * @return
     */
    public Map<String,Object> addCartoonChapter(CartoonChapter cartoonChapter){
        CartoonChapter doubleid = cartoonChapterDao.selectChapterById(cartoonChapter.getCartoonid(),cartoonChapter.getChapterid());
        if(doubleid==null){
            cartoonChapterDao.insertSelective(cartoonChapter);
            return null;
        }
        return null;
    }
    /**
     * 查看更新前6漫画详情
     */
    @Override
    public List<CartoonTable> cartoonUpdateList(){

        return this.cartoonTableDao.selectCartoonUpdate();
    }
    /**
     * 排行榜页面漫画详情
     */
    @Override
    public List<CartoonTable> cartoonRankingList(String ranking){

        return this.cartoonTableDao.selectCartoonRankingAll(ranking);
    }
    /**
     * 排行榜时间排行漫画详情
     */
    @Override
    public List<CartoonTable> cartoonUpdateListAll(){

        return this.cartoonTableDao.selectCartoonUpdateAll();
    }
    /**
     * 查看前十漫画详情
     */
    @Override
    public List<CartoonTable> cartoonScoreList(){

        return this.cartoonTableDao.selectCartoonTen();
    }
    /**
     * 首页漫画排行榜的评分榜数据
     */
    @Override
    public List<CartoonTable> cartoonScoreNotice(){

        return this.cartoonTableDao.selectCartoonNotice();
    }
    /**
     * 首页漫画排行榜的评论榜数据
     */
    @Override
    public List<CartoonTable> cartoonCommentList(){

        return this.cartoonTableDao.selectCartoonComment();
    }
    /**
     * 排行榜评论漫画详情
     */
    @Override
    public List<CartoonTable> cartoonCommentListAll(){

        return this.cartoonTableDao.selectCartoonCommentAll();
    }

    @Override
    public List<CartoonTable> getCartoonByKey(String keyW){

        return cartoonTableDao.selectCartoonByKey(keyW);
    }

    /**
     * 分类得到漫画信息
     * @param cartoonRegion
     * @param cartoonTitle
     * @return
     */
    @Override
    public List<CartoonTable> findCartoonByTitle(String cartoonRegion, String cartoonTitle){
        return cartoonTableDao.selectCartoonByTitle(cartoonRegion, cartoonTitle);
    }
    @Override
    public List<CartoonTable> findCartoonByRe(String cartoonRegion){
        return cartoonTableDao.selectCartoonByRe(cartoonRegion);
    }
    @Override
    public List<CartoonTable> findCartoonByTi(String cartoonTitle){
        return cartoonTableDao.selectCartoonByTi(cartoonTitle);
    }
    /**
     * 通过作者查询他上传漫画的总数
     * @param authorname
     * @return
     */
    public int findCartoonSumByAuthor(String authorname){
        return cartoonTableDao.selectCartoonSumByAuthor(authorname);
    }
    /**
     * 通过作者查询他上传漫画通过的总数
     * @param authorname
     * @return
     */
    public int findPassCartoonSumByAuthor(String authorname){
        return cartoonTableDao.selectPassCartoonSumByAuthor(authorname);
    }
    /**
     * 通过作者查询他上传漫画未通过的总数
     * @param authorname
     * @return
     */
    public int findNotPassCartoonSumByAuthor(String authorname){
        return cartoonTableDao.selectNotPassCartoonSumByAuthor(authorname);
    }

    /**
     * 漫画总量
     * @return
     */
    @Override
    public int getCartoonSum() {

        return cartoonTableDao.selectCartoonSum();
    }

    /**
     * 待审漫画总量
     * @return
     */
    @Override
    public int getNotPassCartoonSum() {
        return cartoonTableDao.selectNotPassCartoonSum();
    }

    /**
     * 国漫总数
     * @return
     */
    @Override
    public int getGuoManSum() {
        return cartoonTableDao.selectGuoManSum();
    }
    /**
     * 日漫总数
     * @return
     */
    @Override
    public int getRiManSum() {
        return cartoonTableDao.selectRiManSum();
    }
}
