package com.scartoon.starcartoon.controller.pages.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.pojo.entity.CartoonComment;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.pojo.entity.CommentLike;
import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.service.CartoonService;
import com.scartoon.starcartoon.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pages/front")
public class CommentPageController {

    @Resource
    CommentService commentService;

    @RequestMapping("comment")
    public String comment(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //设置每页评论数
        PageHelper.startPage(pageNum,5);
        List<CartoonComment> comment=this.commentService.findCommentPassed();
        PageInfo<CartoonComment> pageInfo = new PageInfo<CartoonComment>(comment);
        model.addAttribute("comment",pageInfo);
        return "pages/front/comment/commentPage";
    }

    /**
     * 点赞
     * @param id
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping("like")
    String like(CartoonComment id, int userid, Model model){
        CommentLike commentLike = new CommentLike();
        commentLike.setTimestamp(new Date());
        commentLike.setUserid(userid);
        commentLike.setCommentid(id.getId());
        commentService.addCommentLikeDate(commentLike);
        return "redirect:/pages/front/comment";
    }

    /**
     * 取消点赞
     * @param id
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping("notLike")
    String notLike(CartoonComment id, int userid, Model model){
        CommentLike commentLike = new CommentLike();
        commentLike.setTimestamp(new Date());
        commentLike.setUserid(userid);
        commentLike.setCommentid(id.getId());
        commentService.deleteCommentLikeDate(commentLike);
        return "redirect:/pages/front/comment";
    }

}
