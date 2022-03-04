package com.scartoon.starcartoon.service;

import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.Author;

import java.util.List;
import java.util.Map;

/**
 * 关于作者的一些业务操作
 */
public interface AuthorService {

    /**
     * 作者登录方法
     * @param author
     * @return
     */
    boolean login(Author author);
    /**
     * 作者注册
     */
    boolean registerAuthor(Author author);
    /**
     * 查看所有作者
     * @return
     */
    List<Author> findAuthorAll();

    /**
     * 添加用户的功能，返回res结果true或者false，返回msg为消息
     */
    Map<String,Object> addAuthor (Author author);

    /**
     * 通过id得到作者信息
     * @param id
     * @return
     */
    Author getAuthorById(Integer id);
    /**
     * 通过名称得到作者信息
     * @param authorname
     * @return
     */
    Author getAuthorByName(String authorname);
    /**
     * 修改作者信息
     * @param author
     * @return
     */
    Integer updateAuthor(Author author);

    /**
     * 删除作者
     * @param id
     * @return
     */
    ResponseDTO deleteId(Integer id);

    /**
     * 作者总量
     * @return
     */
    int getAuthorSum();
}
