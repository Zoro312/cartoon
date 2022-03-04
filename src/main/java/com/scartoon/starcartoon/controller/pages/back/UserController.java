package com.scartoon.starcartoon.controller.pages.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户管理的控制器
 */
@Controller
@RequestMapping("pages/back/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     *打开用户添加页面
     */
    @RequestMapping("/add")
    String addUser(){

        return "pages/back/user/addUser";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    @ResponseBody
    Map<String, Object> addUser(User user){

        if("on".equals(user.getLimit())){
            user.setLimit("1");
        } else {
            user.setLimit("0");
        }
        return userService.addUser(user);
    }

    /**
     * 查询全部用户
     */
    @GetMapping("/User")
    public String findUserAll(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //设置每页用户数
        PageHelper.startPage(pageNum,5);
        List<User> list=this.userService.findUserAll();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        model.addAttribute("list",pageInfo);
        return "pages/back/user/user";
    }

    /**
     * 根据用户id获取用户信息
     */
    @RequestMapping("alterid")
    String userId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("user",userService.getUserById(id));
        return "/pages/back/user/alterUser";
    }

    /**
     * 修改用户信息
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/alterUser")
    @ResponseBody
    Integer alterUser(User user,Model model){

        return userService.updateUser(user);
    }

    /**
     *删除用户
     */
    @RequestMapping("/deleteUser")
    String deleteUsers(Integer id){
        userService.deleteId(id);
        return "redirect:/pages/back/user/User";
    }

}
