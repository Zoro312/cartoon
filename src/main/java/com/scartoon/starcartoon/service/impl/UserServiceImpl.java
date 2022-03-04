package com.scartoon.starcartoon.service.impl;

import com.scartoon.starcartoon.dao.UserDao;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.User;
import com.scartoon.starcartoon.service.UserService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserServie接口实现类
 */
@Service  //交给springIoc（容器管理）后边就可以直接装配只用它了
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    /**
     * 用户注册
     */
    @Override
    public boolean registerUser(User user){
        User dbUser = userDao.selectByUserName(user.getUsername());
        User phoneNumber = userDao.selectByPhoneNumber(user.getPhoneNumber());
        if (dbUser == null&&phoneNumber == null) {   //数据库里没有这个人,和手机号，可以注册
                user.setLimit("0");//用户注册，权限默认为普通用户
                int i = userDao.insert(user);
                if (i == 1) {//表示注册成功
                    return true;
                } else {
                    return false;//注册失败
                }
            } else {
            return false;//用户名或手机号存在，注册失败
        }
    }
    /**
     * 前台登录
     *
     * @param user
     * @return
     */
    @Override
    public boolean login(User user) {

        User dbUser = userDao.selectByUserName(user.getUsername());
        if (dbUser == null) {   //数据库里没有这个人，登录失败
            return false;
        } else {
            if (user.getPassword() == null) {  // 如果传递过来的密码为空直接传递失败
                return false;
            }
            if (user.getPassword().equals(dbUser.getPassword())) {   // 如果传过来的密码，等于我查询的密码，登录成功
                return true;
            }
        }

        return false;
    }

    /**
     * 后台登录
     * @param user
     * @return
     */
    @Override
    public boolean loginBack(User user) {

        User dbUser = userDao.selectByUserName(user.getUsername());
        if (dbUser == null) {   //数据库里没有这个人，登录失败
            return false;
        } else {
            if (user.getPassword() == null) {  // 如果传递过来的密码为空直接传递失败
                return false;
            }
            if ("1".equals(dbUser.getLimit())) {
                if (user.getPassword().equals(dbUser.getPassword())) {   // 如果传过来的密码，等于我查询的密码，登录成功
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> addUser(User user) {

        Map<String, Object> resMap = new HashMap<>();
        int i = userDao.insert(user);
        if (i == 1) {//表示增加用户成功
            resMap.put("res", true);//增加成功
            resMap.put("msg", "添加用户成功");//添加用户成功
        } else {
            resMap.put("res", false);//增加失败
            resMap.put("msg", "添加用户失败");//添加用户失败
        }
        return resMap;
    }

    /**
     * 查看所有用户
     *
     * @return
     */
    @Override
    public List<User> findUserAll() {

        return this.userDao.selectUserAll();
    }

    /**
     * 通过id得到用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {

        return userDao.selectByPrimaryKey(id);
    }
    @Override
    public User getUserByName(String username){
        return userDao.selectByUserName(username);
    }
    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        int res;
        int i = userDao.updateByPrimaryKeySelective(user);
        if (i == 1) {//表示增加用户成功
            res = 1;
        } else {
            res = 0;
        }
        return res;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteId(Integer id) {
        if (userDao.deleteByPrimaryKey(id) == 1) {//成功
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 用户总量
     * @return
     */
    @Override
    public int getUserSum() {
        return userDao.selsectUserSum();
    }


}
