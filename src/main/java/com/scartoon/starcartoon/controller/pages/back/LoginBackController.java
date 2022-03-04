package com.scartoon.starcartoon.controller.pages.back;

import com.alibaba.fastjson.JSON;
import com.scartoon.starcartoon.pojo.entity.CartoonType;
import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.service.AuthorService;
import com.scartoon.starcartoon.service.CartoonService;
import com.scartoon.starcartoon.service.TypeService;
import com.scartoon.starcartoon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("pages/back")
public class LoginBackController {

    @Resource
    UserService userService;
    @Resource
    AuthorService authorService;
    @Resource
    CartoonService cartoonService;
    @Resource
    TypeService typeService;

    /**
     * 后台登录页面
     * @return
     */
    @RequestMapping("loginPage")
    String loginPage(){
        return "pages/back/loginBack/loginBackPage";
    }

    /**
     * 验证后台管理员密码执行登录操作的方法
     */
    @RequestMapping("loginBack")
    String loginBack(User user, Model model, HttpSession session) {

        if ("".equals(user.getUsername()) || user.getUsername() == null) { //你连名字都不传，就不用查了，登录失败
            model.addAttribute("errorMsg" , "请输入用户名称登录");
            return "pages/back/loginBack/loginBackPage"; //返回登录页面

        }
        if ("".equals(user.getPassword()) || user.getPassword() == null) { //你连密码都不传，就不用查了，登录失败
            model.addAttribute("errorMsg" , "请输入密码");
            return "pages/back/loginBack/loginBackPage"; //返回登录页面

        }
        boolean loginResult = userService.loginBack(user);
        if (loginResult) {  // 登录成功，返回到后台首页
            session.setAttribute("adminname",userService.getUserByName(user.getUsername()));
            return "pages/back/backHome";
        }
        model.addAttribute("errorMsg" , "登录失败");
        return "pages/back/loginBack/loginBackPage";
    }

    /**
     * 后台主页的数据统计
     */
    @RequestMapping("home")
    String backHome(Model model){
        //用户总量
        model.addAttribute("userSum",userService.getUserSum());
        //作者总量
        model.addAttribute("authorSum",authorService.getAuthorSum());
        //漫画总量
        model.addAttribute("cartoonSum",cartoonService.getCartoonSum());
        //待审漫画总数
        model.addAttribute("notPassCartoonSum",cartoonService.getNotPassCartoonSum());
        //饼状图数据
        model.addAttribute("guoManSum",cartoonService.getGuoManSum());
        model.addAttribute("riManSum",cartoonService.getRiManSum());
        return "pages/back/loginBack/home";
    }
}
