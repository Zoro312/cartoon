package com.scartoon.starcartoon.controller;

import com.scartoon.starcartoon.dao.UserDao;
import com.scartoon.starcartoon.pojo.entity.*;
import com.scartoon.starcartoon.service.CartoonService;
import com.scartoon.starcartoon.service.CommentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    UserDao userDao;
    @Resource
    CartoonService cartoonService;
    @Resource
    CommentService commentService;

    /**
     * 加载首页数据
     * @param model
     * @return
     */
    @RequestMapping("/")
    //@ResponseBody   // 表示返回JSON字符串给前端
    String index(Model model){
        //首页最上面的六个最新更新的数据
        List<CartoonTable> update=this.cartoonService.cartoonUpdateList();
        model.addAttribute("update",update);
        //首页评分榜的数据
        List<CartoonTable> list=this.cartoonService.cartoonScoreList();
        model.addAttribute("list",list);
        //首页漫画排行榜的评分榜数据
        List<CartoonTable> score=this.cartoonService.cartoonScoreNotice();
        model.addAttribute("score",score);
        //首页漫画排行榜的评论榜数据
        List<CartoonTable> comment=this.cartoonService.cartoonCommentList();
        model.addAttribute("comment",comment);
        //User user = userDao.selectByPrimaryKey(1);
        return "pages/front/index";
    }

    /**
     * 漫画详情页
     * @param id
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("cartoonDetails")
    String cartoonDetails (Integer id, HttpSession session,Model model) {
        session.setAttribute("cartoonDetails",cartoonService.getCartoonTableById(id));
        List<CartoonChapter> chaptersId=this.cartoonService.getPassedCartoonChapterById(id);
        List<CartoonComment> findCommentById=commentService.getPassedCommentByCartoonId(id);
        model.addAttribute("comment",findCommentById);
        session.setAttribute("chaptersId",chaptersId);
        return "pages/front/cartoonDetails";
    }

    /**
     * 详情页对漫画评论点赞
     * @param id
     * @param userid
     * @param cartoonid
     * @param model
     * @return
     */
    @RequestMapping("cartoonDetails/like")
    String like(CartoonComment id, int userid,int cartoonid, Model model){
        CommentLike commentLike = new CommentLike();
        commentLike.setTimestamp(new Date());
        commentLike.setUserid(userid);
        commentLike.setCommentid(id.getId());
        commentService.addCommentLikeDate(commentLike);

        List<CartoonComment> findCommentById=commentService.getPassedCommentByCartoonId(cartoonid);
        model.addAttribute("comment",findCommentById);
        return "pages/front/cartoonDetails";
    }

    /**
     * 详情页对漫画评论取消点赞
     * @param id
     * @param userid
     * @param cartoonid
     * @param model
     * @return
     */
    @RequestMapping("cartoonDetails/notLike")
    String notLike(CartoonComment id, int userid,int cartoonid, Model model){
        CommentLike commentLike = new CommentLike();
        commentLike.setTimestamp(new Date());
        commentLike.setUserid(userid);
        commentLike.setCommentid(id.getId());
        commentService.deleteCommentLikeDate(commentLike);

        List<CartoonComment> findCommentById=commentService.getPassedCommentByCartoonId(cartoonid);
        model.addAttribute("comment",findCommentById);
        return "pages/front/cartoonDetails";
    }

    /**
     * 获取漫画章节信息
     * @param cartoonid
     * @param chapterid
     * @param session
     * @return
     */
    @RequestMapping("cartoonChapter")
    String cartoonChapter (Integer cartoonid, Integer chapterid, HttpSession session) {
        session.setAttribute("cartoonChapter",cartoonService.getCartoonChapterByDoubleId(cartoonid,chapterid));
        return "pages/front/cartoonChapter";
    }

    /**
     * 用户评论
     * @param cartoonComment
     * @return
     */
    @RequestMapping("userComment")
    @ResponseBody
    Map<String, Object> userComment(CartoonComment cartoonComment){
        cartoonComment.setTimestamp(new Date());
        cartoonComment.setState(0);
        cartoonComment.setLikesum(0);
        return commentService.addComment(cartoonComment);
    }

    /**
     * 首页搜索功能
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("findByKey")
    String findByKey(HttpServletRequest request, HttpSession session){
        String keyW = request.getParameter("keyWord");
        session.setAttribute("cartoonDetails",cartoonService.getCartoonByKey(keyW));
        return "pages/front/cartoonDetails";
    }

    /**
     * 注销
     * @param session
     * @param sessionStatus
     * @return
     */
    @RequestMapping("logout")
    String logout(HttpSession session, SessionStatus sessionStatus){
        session.removeAttribute("username");
        return "redirect:/";
    }


}
