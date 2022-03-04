package com.scartoon.starcartoon.service;

import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.CartoonType;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 关于用户的一些业务操作
 */
public interface TypeService {

    /**
     * 查看所有类型
     * @return
     */
    List<CartoonType> findTypeAll();

    /**
     * 查看地区类型
     * @return
     */
    List<CartoonType> findTypeRegion();

    /**
     * 查看题材类型
     * @return
     */
    List<CartoonType> findTypeTitle();
    /**
     * 查看进度类型
     * @return
     */
    List<CartoonType> findTypeState();
    /**
     * 查看排序类型
     * @return
     */
    List<CartoonType> findTypeSort();

    /**
     * 添加类型的功能，返回res结果true或者false，返回msg为消息
     */
    Map<String,Object> addType(CartoonType type);
    /**
     * 通过id得到类型信息
     * @param id
     * @return
     */
    CartoonType getTypeById(Integer id);

    /**
     * 修改类型信息
     * @param type
     * @return
     */
    Integer updateType(CartoonType type);

    /**
     * 删除类型
     * @param id
     * @return
     */
    ResponseDTO deleteId(Integer id);



}
