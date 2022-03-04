package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserDao {
    // 根据主键删除
    int deleteByPrimaryKey(Integer userid);

    // 插入数据
    int insert(User record);

    // 选择性插入
    int insertSelective(User record);

    // 根据主键查询
    User selectByPrimaryKey(Integer userid);

    // 根据主键选择性的更新，传了值的字段（属性），就更新，否则，就不更新这个字段
    int updateByPrimaryKeySelective(User record);

    // 更新全部
    int updateByPrimaryKey(User record);

    /**
     * 根据真实名称查询用户信息
     *
     * @param realName 传递的真实姓名的值
     * @return 返回一个User对象
     */
    User selectUserByRealName(String realName);

    /**
     * 根据用户名称查询这个用户的基本信息
     *
     * @param username
     * @return
     */
    User selectByUserName(String username);

    /**
     * 根据用户手机查询这个用户的基本信息
     *
     * @param phoneNumber
     * @return
     */
    User selectByPhoneNumber(String phoneNumber);

    /**
     * 查看所有用户
     * @return
     */
    List<User> selectUserAll();

    /**
     * 用户总量
     * @return
     */
    int selsectUserSum();
}