package com.scartoon.starcartoon.controller;

import com.scartoon.starcartoon.consts.CartoonConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;



public class BaseController {
 Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 上传文件
     * @param uploadDir
     * @param f
     * @return
     */
 protected String uploadFile(String uploadDir, MultipartFile f) {
        String realPath = CartoonConsts.getUploadPath();
        String uploadPath = uploadDir + UUID.randomUUID() + "." + f.getContentType().split("/")[1];
        File file = new File(realPath + uploadPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if (!f.isEmpty()) {
                if (f.getSize() > 0) {
                    f.transferTo(file);
                    logger.info("图片已保存到:" + realPath + uploadPath);
                    return uploadPath;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CartoonConsts.NO_UPLOAD_IMG;
    }

    protected String uploadFiles(String uploadPath, MultipartFile[] imgs) {
        String realPath = CartoonConsts.getUploadPath();
        StringBuffer uploadFilesStr = new StringBuffer();
        for (MultipartFile img : imgs) {
            String dbPath = uploadPath + UUID.randomUUID() + "." + img.getContentType();
            uploadFilesStr.append(dbPath).append("|");
            File file = new File(realPath + dbPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                img.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //删除最后一个|线
        uploadFilesStr.delete(uploadFilesStr.length() - 1, uploadFilesStr.length());
        return new String(uploadFilesStr);
    }

    protected void setErrorMsg(Object msg) {
        getRequest().setAttribute("msg", msg);
    }

    public static boolean deleteFile(String uploadPath) {
        File f = new File(CartoonConsts.getUploadPath() + uploadPath);
        return f.delete();
    }

    /*获取request对象*/
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return this.getRequest().getSession();
    }

    /*获取userid*/
    protected Integer getUserId() {
        return (Integer) getSession().getAttribute("userId");
    }

    /**
     * 多图上传存放的相对路径
     * @param file      文件
     * @param path      文件存放路径
     * @param fileName  保存的文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName) {

        //确定上传的文件名
        String realPath = path + "\\" + fileName;
        System.out.println("上传文件：" + realPath);

        File fileNew = new File("F:\\cartoon\\chapter\\");
        if(!fileNew.getParentFile().exists()){
            boolean result = fileNew.getParentFile().mkdirs();
            if(!result){
                throw new RuntimeException("创建文件路径失败");
            }
        }
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
