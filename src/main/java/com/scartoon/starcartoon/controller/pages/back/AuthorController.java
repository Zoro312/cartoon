package com.scartoon.starcartoon.controller.pages.back;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.pojo.entity.Author;
import com.scartoon.starcartoon.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 作者管理的控制器
 */
@Controller
@RequestMapping("pages/back/author")
public class AuthorController {

    @Resource
    AuthorService authorService;

    /**
     * 查询全部作者
     * @param model
     * @return
     */
    @GetMapping("/Author")
    public String findAuthorAll(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //设置每页作者数
        PageHelper.startPage(pageNum,5);
        List<Author> list=this.authorService.findAuthorAll();
        PageInfo<Author> pageInfo = new PageInfo<Author>(list);
        model.addAttribute("list",pageInfo);
        return "pages/back/author/author";
    }

    /**
     *打开作者添加页面
     */
    @RequestMapping("/add")
    String addUser(){

        return "pages/back/author/addAuthor";
    }

    /**
     * 添加作者
     */
    @RequestMapping("/addAuthor")
    @ResponseBody
    Map<String, Object> addUser(Author author){

        return authorService.addAuthor(author);
    }

    /**
     * 根据作者id获取作者信息
     */
    @RequestMapping("alterid")
    String alterId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("author",authorService.getAuthorById(id));
        return "/pages/back/author/alterAuthor";
    }

    /**
     * 修改作者信息
     * @param author
     * @param model
     * @return
     */
    @RequestMapping("alterAuthor")
    @ResponseBody
    Integer  alterUser(Author author, Model model){

        return authorService.updateAuthor(author);
    }

    /**
     *删除用户
     */
    @RequestMapping("/deleteAuthor")
    String deleteAuthor(Integer id){
        authorService.deleteId(id);
        return "redirect:/pages/back/author/Author";
    }
}
