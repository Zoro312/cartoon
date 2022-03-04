package com.scartoon.starcartoon.controller.pages.front;

import com.alibaba.fastjson.JSON;
import com.scartoon.starcartoon.controller.BaseController;
import com.scartoon.starcartoon.pojo.entity.Author;
import com.scartoon.starcartoon.pojo.entity.CartoonChapter;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.service.AuthorService;
import com.scartoon.starcartoon.service.CartoonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("pages/front")
public class AuthorPageController {

    @Resource
    AuthorService authorService;
    @Resource
    CartoonService cartoonService;
    //多图上传时使用
    private List<Map<String, Object>> root;

    /**
     * 作者主页
     */
    @RequestMapping("home")
    public String authorHome(String authorname,Model model){
        int allCartoon=this.cartoonService.findCartoonSumByAuthor(authorname);
        int passCartoon=this.cartoonService.findPassCartoonSumByAuthor(authorname);
        int notPassCartoon=this.cartoonService.findNotPassCartoonSumByAuthor(authorname);
        model.addAttribute("allCartoon",allCartoon);
        model.addAttribute("passCartoon",passCartoon);
        model.addAttribute("notPassCartoon",notPassCartoon);
        return "pages/front/author/authorHome";
    }

    /**
     * 作者基本资料
     */
    @RequestMapping("authorInfo")
    public String authorInfo(String authorname,Model model){

        model.addAttribute("authorInfo",authorService.getAuthorByName(authorname));
        return "pages/front/author/authorInfo";
    }

    /**
     * 作者登录
     * @param author
     * @param model
     * @return
     */
    @RequestMapping("loginAuthor")
    public String loginAuthor(Author author, Model model, HttpSession session){
        String login = author.getAuthorname();
        if ("".equals(author.getAuthorname()) || author.getAuthorname() == null) { //你连名字都不传，就不用查了，登录失败
            model.addAttribute("errorMsg" , "请输入作者名称登录");
            return "pages/front/loginFront/loginAuthorPage"; //返回登录页面
        }
        if ("".equals(author.getPassword()) || author.getPassword() == null) { //你连名字都不传，就不用查了，登录失败
            model.addAttribute("errorMsg" , "密码不能为空");
            return "pages/front/loginFront/loginAuthorPage"; //返回登录页面
        }
        boolean loginResult = authorService.login(author);
        if (loginResult) {  // 登录成功，返回到后台首页
            session.setAttribute("authorname",authorService.getAuthorByName(author.getAuthorname()));
            return "pages/front/author/authorPage";
        }
        model.addAttribute("errorMsg" , "登录失败，作者名或密码错误");
        return "pages/front/loginFront/loginAuthorPage";
    }

    /**
     * 作者注册
     */
    @RequestMapping("registerAuthor")
    public String registerAuthor(Author author, Model model) {
        if ("".equals(author.getAuthorname()) || author.getAuthorname() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "用户名不能为空");
            return "pages/front/loginFront/registerAuthorPage"; //返回注册页面
        }
        if ("".equals(author.getPassword()) || author.getPassword() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "密码不能为空");
            return "pages/front/loginFront/registerAuthorPage"; //返回注册页面
        }
        if ("".equals(author.getIdcard()) || author.getIdcard() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "身份证号不能为空");
            return "pages/front/loginFront/registerAuthorPage"; //返回注册页面
        }
        if ("".equals(author.getAddress()) || author.getAddress() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "地址不能为空");
            return "pages/front/loginFront/registerAuthorPage"; //返回注册页面
        }
        if ("".equals(author.getPhoneNumber()) || author.getPhoneNumber() == null) { //你连名字都不传，就不用查了，注册失败
            model.addAttribute("errorMsg" , "手机号不能为空");
            return "pages/front/loginFront/registerAuthorPage"; //返回注册页面
        }

        boolean loginResult = authorService.registerAuthor(author);
        if (loginResult) {  // 注册成功，返回到登录
            return "pages/front/loginFront/loginAuthorPage";
        }else {
            model.addAttribute("errorMsg" , "注册失败，作者名或手机号已注册过");
            return "pages/front/loginFront/registerUserPage";
        }

    }

    /**
     * 作者注销
     */
    @RequestMapping("authorLogout")
    String logout(HttpSession session, SessionStatus sessionStatus){
        session.removeAttribute("authorname");
        return "pages/front/loginFront/loginAuthorPage";
    }

    /**
     *作者打开漫画添加页面
     */
    @RequestMapping("addCartoon")
    String addCartoon(){

        return "redirect:/pages/back/type/title";
    }
    /**
     * 作者上传漫画的详情列表
     */
    @RequestMapping("cartoonList")
    public String findCartoonAll(String authorname,Model model){
        List<CartoonTable> list=this.cartoonService.findCartoonByAuthor(authorname);
        model.addAttribute("list",list);
        return "pages/front/author/authorCartoonList";
    }

    /**
     *作者添加漫画章节
     * 根据漫画id获取漫画章节信息
     */
    @RequestMapping("/addChapter")
    String addCartoonChapter(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("cartoonChapter",cartoonService.getCartoonTableById(id));
        List<CartoonChapter> cartoonId=this.cartoonService.getCartoonChapterById(id);
        session.setAttribute("cartoonId",cartoonId);
        return "/pages/front/author/authorAddChapter";
    }

    /**
     * 根据漫画id获取漫画信息
     */
    @RequestMapping("alterCartoonById")
    String alterId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("cartoonTable",cartoonService.getCartoonTableById(id));
        return "/pages/front/author/authorAlterCartoon";
    }

    /**
     * 根据id获取漫画章节信息
     * @param cartoonid
     * @param chapterid
     * @param session
     * @return
     */
    @RequestMapping("getChapterById")
    String alterChapterId(Integer cartoonid, Integer chapterid, HttpSession session){
        session.setAttribute("cartoonChapter",cartoonService.getCartoonChapterByDoubleId(cartoonid,chapterid));
        return "/pages/front/author/authorAlterChapter";
    }

    /**
     * 多图上传（漫画章节内容上传）
     */
    @RequestMapping("/alterChapter")
    @ResponseBody
    public List multipleImageUpload(@RequestParam("uploadFiles") MultipartFile[] files , CartoonChapter cartoonChapter){
        System.out.println("上传的图片数："+files.length);
        root = new ArrayList<Map<String,Object>>();
        String str="";
        for (MultipartFile file : files) {    //循环保存文件
            Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
            String result_msg="";//上传结果信息
            if (file.getSize() / 1000 > 1000){
                result_msg="图片大小不能超过1000KB";
            }
            else{
                //判断上传文件格式
                String fileType = file.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    // 要上传的目标文件存放的绝对路径
                    final String localPath="F:\\cartoon\\chapter";
                    //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                    //获取文件名
                    String fileName = file.getOriginalFilename();
                    //获取文件后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    //重新生成文件名
                    fileName = UUID.randomUUID()+suffixName;
                    if (BaseController.upload(file, localPath, fileName)) {
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        cartoonChapter.setChapterPath(fileName);
                        //fileName = "\\upload\\"+fileName;
                        str+=fileName+",";
                        String relativePath=fileName;
                        result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                        result_msg="图片上传成功";
                    }
                    else{
                        result_msg="图片上传失败";
                    }
                }
                else{
                    result_msg="图片格式不正确";
                }
            }
            result.put("result_msg",result_msg);
            root.add(result);
        }
        cartoonChapter.setChapterPath(str);
        String root_json= JSON.toJSONString(root);
        //System.out.println(root_json);
        cartoonService.updateCartoonChapter(cartoonChapter);
        return root;
    }
}
