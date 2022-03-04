package com.scartoon.starcartoon.controller.pages.front;

import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 这个类是一个登录的控制器，完成登录检验操作等功能
 */
@Controller
@RequestMapping("/pages/front")
public class LoginController {

    @Resource
    UserService userService;

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("registerUserPage")
    String userRegisterPage() {

        return "pages/front/loginFront/registerUserPage";
    }

    /**
     * 验证用户注册的操作
     * @return
     */
    @RequestMapping("registerUser")
    String registerUser (User user,Model model) {
        if ("".equals(user.getUsername()) || user.getUsername() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "用户名不能为空");
            return "pages/front/loginFront/registerUserPage"; //返回注册页面
        }
        if ("".equals(user.getPhoneNumber()) || user.getPhoneNumber() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "手机号不能为空");
            return "pages/front/loginFront/registerUserPage"; //返回注册页面
        }
        if (user.getPhoneNumber().length()>11) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "手机号错误");
            return "pages/front/loginFront/registerUserPage"; //返回注册页面
        }
        if ("".equals(user.getPassword()) || user.getPassword() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "密码不能为空");
            return "pages/front/loginFront/registerUserPage"; //返回注册页面
        }

        boolean loginResult = userService.registerUser(user);
        if (loginResult) {  // 注册成功，返回到前台
            return "redirect:/pages/front/loginUserPage";
        }else {
            model.addAttribute("errorMsg" , "注册失败，用户名或手机号已注册过");
            return "pages/front/loginFront/registerUserPage";
        }
    }

    /**
     * 用户登录页面
     * @return
     */
    @RequestMapping("loginUserPage")
    String loginPage() {
        return "pages/front/loginFront/loginUserPage";
    }

    @RequestMapping("backHome")
    String backHome(){return "pages/back/backHome.html";}

    /**
     * 验证用户密码执行用户登录操作的方法
     */
    @RequestMapping("loginUser")
    String login(User user, Model model, HttpSession session) {

        if ("".equals(user.getUsername()) || user.getUsername() == null) { //你连名字都不传，就不用查了，登录失败
            model.addAttribute("errorMsg" , "请输入用户名称登录");
            return "pages/front/loginFront/loginUserPage"; //返回登录页面
        }
        boolean loginResult = userService.login(user);
        if (loginResult) {  // 登录成功，返回到后台首页
            session.setAttribute("username",userService.getUserByName(user.getUsername()));
            return "redirect:/";
        }
        model.addAttribute("errorMsg" , "登录失败，用户名或密码错误");
        return "pages/front/loginFront/loginUserPage";
    }
}
