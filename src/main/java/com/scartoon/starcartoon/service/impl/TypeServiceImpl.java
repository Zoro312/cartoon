package com.scartoon.starcartoon.service.impl;

import com.scartoon.starcartoon.pojo.entity.CartoonType;
import com.scartoon.starcartoon.dao.CartoonTypeDao;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TypeServie接口实现类
 */
@Service  //交给springIoc（容器管理）后边就可以直接装配只用它了
public class TypeServiceImpl implements TypeService {

    @Resource
    CartoonTypeDao cartoonTypeDao;

    /**
     * 查看所有类型
     * @return
     */
    @Override
    public List<CartoonType> findTypeAll() {

        return this.cartoonTypeDao.selectTypeAll();
    }

    /**
     * 查看题材类型
     */
    @Override
    public List<CartoonType> findTypeTitle() {

        return this.cartoonTypeDao.selectTypeTitle();
    }
    /**
     * 查看地区类型
     * @return
     */
    @Override
    public List<CartoonType> findTypeRegion() {

        return this.cartoonTypeDao.selectTypeRegion();
    }
    /**
     * 查看进度类型
     * @return
     */
    @Override
    public List<CartoonType> findTypeState() {

        return this.cartoonTypeDao.selectTypeState();
    }
    /**
     * 查看排序类型
     * @return
     */
    @Override
    public List<CartoonType> findTypeSort() {

        return this.cartoonTypeDao.selectTypeSort();
    }
    /**
     * 添加类型
     * @param type
     * @return
     */
     @Override
     public Map<String, Object> addType(CartoonType type) {

     Map<String, Object> resMap = new HashMap<>();
     int i = cartoonTypeDao.insertSelective(type);
     if(i==1){//表示增加用户成功
        resMap.put("res",true);//增加成功
        resMap.put("msg","添加类型成功");//添加用户成功
     }else{
        resMap.put("res",false);//增加失败
        resMap.put("msg","添加类型失败");//添加用户失败
     }
     return resMap;
     }
    /**
     * 通过id得到类型信息
     * @param id
     * @return
     */
    @Override
    public CartoonType getTypeById(Integer id) {

        return cartoonTypeDao.selectByPrimaryKey(id);
    }

    /**
     * 修改类型信息
     * @param type
     * @return
     */
    @Override
    public Integer updateType(CartoonType type) {
        int res;
        int i = cartoonTypeDao.updateByPrimaryKeySelective(type);
        if(i==1){//表示修改成功
            res=1;
        }else{
            res=0;
        }
        return res;
    }
    /**
     * 删除类型
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteId(Integer id) {
        if (cartoonTypeDao.deleteByPrimaryKey(id) == 1) {//成功
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

}
