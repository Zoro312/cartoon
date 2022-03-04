package com.scartoon.starcartoon.dao;

import com.scartoon.starcartoon.pojo.entity.CartoonType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.util.List;

@Mapper
@Service
public interface CartoonTypeDao {
    int deleteByPrimaryKey(Integer typeid);

    int insert(CartoonType record);

    int insertSelective(CartoonType record);

    CartoonType selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(CartoonType record);

    int updateByPrimaryKey(CartoonType record);

    /**
     * 查看所有类型
     * @return
     */
    List<CartoonType> selectTypeAll();

    /**
     * 查看地区类型
     * @return
     */
    List<CartoonType> selectTypeRegion();

    /**
     * 查看题材类型
     * @return
     */
    List<CartoonType> selectTypeTitle();

    /**
     * 查看进度类型
     * @return
     */
    List<CartoonType> selectTypeState();

    /**
     * 查看排序类型
     * @return
     */
    List<CartoonType> selectTypeSort();

}