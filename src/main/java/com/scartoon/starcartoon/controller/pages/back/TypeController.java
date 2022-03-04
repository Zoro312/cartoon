package com.scartoon.starcartoon.controller.pages.back;

import com.scartoon.starcartoon.pojo.entity.CartoonType;
import com.scartoon.starcartoon.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户管理的控制器
 */
@Controller
@RequestMapping("pages/back/type")
public class TypeController {

    @Resource
    TypeService typeService;

    /**
     * 查看漫画类型列表
     */
    @GetMapping("/List")
    public String findTypeAll(Model model){
        List<CartoonType> list=this.typeService.findTypeAll();
        model.addAttribute("list",list);
        List<CartoonType> region=this.typeService.findTypeRegion();
        model.addAttribute("region",region);
        return "pages/back/type/typeList";
    }

    /**
     * 查看漫画地区、题材类型列表,返回后台添加漫画页面
     */
    @GetMapping("/region")
    public String findTypeTitle(Model model){
        List<CartoonType> region=this.typeService.findTypeRegion();
        List<CartoonType> title=this.typeService.findTypeTitle();
        model.addAttribute("region",region);
        model.addAttribute("title",title);
        return "pages/back/cartoon/addCartoon";
    }

    /**
     * 查看漫画地区、题材类型列表,返回前台作者添加漫画页面
     */
    @GetMapping("/title")
    public String findTypeRegion(Model model){
        List<CartoonType> region=this.typeService.findTypeRegion();
        List<CartoonType> title=this.typeService.findTypeTitle();
        model.addAttribute("region",region);
        model.addAttribute("title",title);
        return "pages/front/author/authorAddCartoon";
    }

    /**
     *打开类型添加页面
     */
    @RequestMapping("/Add")
    String addUser(){

        return "pages/back/type/addType";
    }

    /**
     * 添加类型
     */
     @RequestMapping("/addType")
     @ResponseBody
     Map<String, Object> addType(CartoonType type){

     return typeService.addType(type);
     }

    /**
     * 根据类型id获取类型信息
     */
    @RequestMapping("alterid")
    String alterId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("type",typeService.getTypeById(id));
        return "/pages/back/type/alterType";
    }

    /**
     * 修改类型信息
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("alterType")
    @ResponseBody
    Integer  alterType(CartoonType type, Model model){

        return typeService.updateType(type);
    }

    /**
     *删除类型
     */
    @RequestMapping("/deleteType")
    String deleteType(Integer id){
        typeService.deleteId(id);
        return "redirect:/pages/back/type/List";
    }
}
