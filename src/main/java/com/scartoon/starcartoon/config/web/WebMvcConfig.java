package com.scartoon.starcartoon.config.web;

import com.scartoon.starcartoon.consts.CartoonConsts;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Mapper
@SpringBootApplication
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    //跨域请求
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS").allowCredentials(false).maxAge(3600);
    }

    //虚拟路径映射到磁盘真正路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
        String docPath = CartoonConsts.getConfigUploadPath();
        logger.info("图片上传路径为：" + docPath);
        /**
         * addResoureHandler：指的是对外暴露的访问路径
         * addResourceLocations：指的是内部文件放置的目录
         */
        registry.addResourceHandler("/cartoon/**").addResourceLocations(docPath);
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/upload/**").addResourceLocations("file:F:/cartoon/chapter/");
    }
}
