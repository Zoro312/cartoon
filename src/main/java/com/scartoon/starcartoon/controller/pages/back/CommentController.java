package com.scartoon.starcartoon.controller.pages.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.pojo.entity.CartoonComment;
import com.scartoon.starcartoon.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论的控制器
 */
@Controller
@RequestMapping("pages/back/comment")
public class CommentController {

    @Resource
    CommentService commentService;

    /**
     * 查看全部评论
     * @param model
     * @return
     */
    @RequestMapping("/List")
    public String findCommentAll (String state, HttpSession session,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //设置每页评论数
        PageHelper.startPage(pageNum,3);
        List<CartoonComment> list=this.commentService.findCommentByState(state);
        PageInfo<CartoonComment> pageInfo = new PageInfo<CartoonComment>(list);
        model.addAttribute("list",pageInfo);
        session.setAttribute("con",state);
        return "pages/back/comment/listComment";
    }

    /**
     * 根据评论id获取评论信息
     */
    @RequestMapping("alterid")
    String alterId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("comment",commentService.getCommentById(id));
        return "/pages/back/comment/alterComment";
    }

    /**
     * 修改评论信息
     * @param cartoonComment
     * @param model
     * @return
     */
    @RequestMapping("alterComment")
    @ResponseBody
    Integer  alterUser(CartoonComment cartoonComment, Model model){
        return commentService.updateComment(cartoonComment);
    }

    /**
     *删除评论
     */
    @RequestMapping("/deleteComment")
    @ResponseBody
    Integer deleteComment(Integer id){

        return commentService.deleteId(id);
    }

}
