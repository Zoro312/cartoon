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
public class RankingController {

    @Resource
    TypeService typeService;
    @Resource
    CartoonService cartoonService;

    @RequestMapping("ranking")
    public String ranking(String ranking, HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        //排序内容
        List<CartoonType> sort=this.typeService.findTypeSort();
        model.addAttribute("sort",sort);
        //设置每页漫画数
        PageHelper.startPage(pageNum,5);
        List<CartoonTable> cartoonAll=this.cartoonService.cartoonRankingList(ranking);
        PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(cartoonAll);
        model.addAttribute("all",pageInfo);
        session.setAttribute("con", ranking);
        return "pages/front/ranking/rankingPage";
    }
}
