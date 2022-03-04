package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface AuthorDao {
    int deleteByPrimaryKey(Integer authorid);

    int insert(Author record);

    int insertSelective(Author record);
    // 根据主键查询
    Author selectByPrimaryKey(Integer authorid);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

    /**
     * 查看所有作者
     * @return
     */
    List<Author> selectAuthorAll();

    /**
     * 根据作者名称查看作者信息
     * @param authorname
     * @return
     */
    Author selectByAuthorName(String authorname);

    /**
     * 根据作者手机号查看作者信息
     * @param phoneNumber
     * @return
     */
    Author selectByPhoneNumber(String phoneNumber);

    /**
     * 根据作者身份证号查看作者信息
     * @param idcard
     * @return
     */
    Author selectByIdCard(String idcard);

    /**
     * 作者总量
     * @return
     */
    int selectAuthorSum();
}