package com.scartoon.starcartoon.service;

import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import org.apache.ibatis.annotations.Results;


import java.util.List;
import java.util.Map;

/**
 * 关于用户的一些业务操作
 */
public interface UserService {

    /**
     * 用户登录方法
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 用户注册
     */
    boolean registerUser(User user);

    /**
     * 后台登陆方法
     * @param user
     * @return
     */
    boolean loginBack(User user);

    /**
     * 添加用户的功能，返回res结果true或者false，返回msg为消息
     */
    Map<String,Object> addUser(User user);

    /**
     * 查看所有用户
     * @return
     */
    List<User> findUserAll();

    /**
     * 通过id得到用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 通过名字得到用户信息
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    ResponseDTO deleteId(Integer id);

    /**
     * 用户的总量
     * @return
     */
    int getUserSum();
}
