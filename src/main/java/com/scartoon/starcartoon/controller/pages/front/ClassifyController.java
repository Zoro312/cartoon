package com.scartoon.starcartoon.controller.pages.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.pojo.entity.CartoonType;
import com.scartoon.starcartoon.service.CartoonService;
import com.scartoon.starcartoon.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("pages/front")
public class ClassifyController {

    @Resource
    TypeService typeService;
    @Resource
    CartoonService cartoonService;

    @RequestMapping("classify")
    public String classify(CartoonTable cartoon, Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<CartoonType> region = this.typeService.findTypeRegion();
        List<CartoonType> title = this.typeService.findTypeTitle();
        model.addAttribute("region", region);
        model.addAttribute("title", title);
        //设置每页漫画数
        PageHelper.startPage(pageNum,5);
        if (cartoon.getRegionType()==null && cartoon.getRegionType()==null) {
            List<CartoonTable> cartoonAll = this.cartoonService.findCartoonAll();
            PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
            session.setAttribute("con", cartoon);
            model.addAttribute("all",pageInfo);
            return "pages/front/classify/classifyPage";
        }
        if ("".equals(cartoon.getTitleType()) && "".equals(cartoon.getRegionType())) {
            List<CartoonTable> cartoonAll = this.cartoonService.findCartoonAll();
            PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
            session.setAttribute("con", cartoon);
            model.addAttribute("all", pageInfo);
            return "pages/front/classify/classifyPage";
        }
        if ("".equals(cartoon.getTitleType()) || "null".equals(cartoon.getTitleType())) {
            List<CartoonTable> cartoonAll = this.cartoonService.findCartoonByRe(cartoon.getRegionType());
            PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
            session.setAttribute("con", cartoon);
            model.addAttribute("all", pageInfo);
            return "pages/front/classify/classifyPage";
        }
        if ("".equals(cartoon.getRegionType()) || "null".equals(cartoon.getRegionType())) {
            List<CartoonTable> cartoonAll = this.cartoonService.findCartoonByTi(cartoon.getTitleType());
            PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
            session.setAttribute("con", cartoon);
            model.addAttribute("all", pageInfo);
            return "pages/front/classify/classifyPage";
        }
        List<CartoonTable> cartoonAll = this.cartoonService.findCartoonByTitle(cartoon.getRegionType(), cartoon.getTitleType());
        PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
        session.setAttribute("con", cartoon);
        model.addAttribute("all", pageInfo);
        return "pages/front/classify/classifyPage";
    }

}
