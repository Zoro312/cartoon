package com.scartoon.starcartoon.controller.pages.back;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scartoon.starcartoon.controller.BaseController;
import com.scartoon.starcartoon.pojo.entity.CartoonChapter;
import com.scartoon.starcartoon.pojo.entity.CartoonTable;
import com.scartoon.starcartoon.service.CartoonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("pages/back/cartoon")
public class CartoonController extends BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    CartoonService cartoonService;
    //多图上传时使用
    private List<Map<String, Object>> root;

    /**
     *打开漫画添加页面
     */
    @RequestMapping("/addCartoon")
    String addCartoon(){

        return "redirect:/pages/back/type/region";
    }

    /**
     * 添加漫画详情
     */
    @RequestMapping("/addCartoonTable")
    @ResponseBody
    Map<String, Object> addCartoon(CartoonTable cartoon, MultipartFile pic){
        //进度
        if("on".equals(cartoon.getState())){
            cartoon.setState("连载中");
        } else {
            cartoon.setState("完结");
        }
        //状态
        if("on".equals(cartoon.getLimit())){
            cartoon.setLimit("通过");
        } else {
            cartoon.setLimit("未通过");
        }
        cartoon.setTimestamp(new Date());//时间

        /**
         * 插入封面
         */
        String imgUrl = uploadFile("/cartoon/cover/",pic);
        cartoon.setImagepath(imgUrl);
        return cartoonService.addCartoon(cartoon);
    }

    /**
     * 漫画详情列表
     */
    @RequestMapping("/List")
    public String findCartoonAll(String state, HttpSession session,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //设置每页漫画数
        PageHelper.startPage(pageNum,4);
        List<CartoonTable> list=this.cartoonService.findCartoonByState(state);
        PageInfo<CartoonTable> pageInfo = new PageInfo<CartoonTable>(list);
        model.addAttribute("list",pageInfo);
        session.setAttribute("con",state);
        return "pages/back/cartoon/listCartoon";
    }

    /**
     * 根据漫画id获取漫画信息
     */
    @RequestMapping("alterid")
    String alterId(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("cartoonTable",cartoonService.getCartoonTableById(id));
        return "/pages/back/cartoon/alterCartoonTable";
    }

    /**
     * 修改漫画信息
     * @param
     * @param model
     * @return
     */
    @RequestMapping("alterCartoonTable")
    @ResponseBody
    Integer  alterCartoonTable(CartoonTable cartoon, Model model,MultipartFile pic){
        if(pic.getSize()>0){  //如果图片发生修改就执行
            String imgUrl = uploadFile("/cartoon/cover/",pic);
            cartoon.setImagepath(imgUrl);
        }
        return cartoonService.updateCartoonTable(cartoon);
    }

    /**
     * 根据id获取漫画章节信息
     * @param cartoonid
     * @param chapterid
     * @param session
     * @return
     */
    @RequestMapping("alterChapterId")
    String alterChapterId(Integer cartoonid, Integer chapterid, HttpSession session){
        session.setAttribute("cartoonChapter",cartoonService.getCartoonChapterByDoubleId(cartoonid,chapterid));
        return "/pages/back/cartoon/alterCartoonChapter";
    }

    /**
     * 修改漫画章节信息
     * @param chapter
     * @return
     */
    @RequestMapping("alterCartoonChapter")
    @ResponseBody
    Integer  alterCartoonChapter(CartoonChapter chapter){
        return cartoonService.updateCartoonChapter(chapter);
    }

    /**
     *删除漫画详情
     */
    @RequestMapping("/deleteCartoonTable")
    @ResponseBody
    Integer deleteCartoonTable(Integer id){

        return cartoonService.deleteId(id);

    }

    /**
     *添加漫画章节
     * 根据漫画id获取漫画章节信息
     */
    @RequestMapping("/add")
    String addCartoonChapter(Integer id, HttpSession session){
        session.setAttribute("id",id);
        session.setAttribute("cartoonChapter",cartoonService.getCartoonTableById(id));
        List<CartoonChapter> cartoonId=this.cartoonService.getCartoonChapterById(id);
        session.setAttribute("cartoonId",cartoonId);
        return "/pages/back/cartoon/addCartoonChapter";
    }

    /**
     * 多图上传（漫画章节内容上传）
     */
    @RequestMapping("/multipleImageUpload")
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
        cartoonService.addCartoonChapter(cartoonChapter);
        return root;
    }



}
