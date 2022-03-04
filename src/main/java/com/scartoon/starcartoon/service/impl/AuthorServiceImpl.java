package com.scartoon.starcartoon.service.impl;

import com.scartoon.starcartoon.dao.AuthorDao;
import com.scartoon.starcartoon.pojo.dto.ResponseDTO;
import com.scartoon.starcartoon.pojo.entity.Author;
import com.scartoon.starcartoon.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AuthorServie接口实现类
 */
@Service//交给springIoc（容器管理）后边就可以直接装配只用它了
public class AuthorServiceImpl implements AuthorService {

    @Resource
    AuthorDao authorDao;

    /**
     * 作者登录
     * @param author
     * @return
     */
    @Override
    public boolean login(Author author) {

        Author dbAuthor = authorDao.selectByAuthorName(author.getAuthorname());
        if (dbAuthor == null) {   //数据库里没有这个人，登录失败
            return false;
        } else {
            if (author.getPassword() == null) {  // 如果传递过来的密码为空直接传递失败
                return false;
            }
            if (author.getPassword().equals(dbAuthor.getPassword())) {   // 如果传过来的密码，等于我查询的密码，登录成功
                return true;
            }
        }

        return false;
    }
    /**
     * 作者注册
     */
    @Override
    public boolean registerAuthor(Author author){
        Author dbAuthor = authorDao.selectByAuthorName(author.getAuthorname());
        Author phoneNumber = authorDao.selectByPhoneNumber(author.getPhoneNumber());
        Author idcard = authorDao.selectByIdCard(author.getIdcard());
        if (dbAuthor == null && phoneNumber == null && idcard == null) {   //数据库里没有这个人,和手机号，可以注册
            int i = authorDao.insert(author);
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
     * 查看所有作者
     * @return
     */

    @Override
    public List<Author> findAuthorAll() {

        return this.authorDao.selectAuthorAll();
    }

    /**
     * 添加作者
     * @param
     * @return
     */
    @Override
    public Map<String, Object> addAuthor(Author author) {

        Map<String, Object> resMap = new HashMap<>();
        int i = authorDao.insertSelective(author);
        if(i==1){//表示增加用户成功
            resMap.put("res",true);//增加成功
            resMap.put("msg","添加成功");//添加用户成功
        }else{
            resMap.put("res",false);//增加失败
            resMap.put("msg","添加失败");//添加用户失败
        }
        return resMap;
    }

    /**
     * 通过id得到作者信息
     * @param id
     * @return
     */
    @Override
    public Author getAuthorById(Integer id) {

        return authorDao.selectByPrimaryKey(id);
    }
    /**
     * 通过id得到作者信息
     * @param authorname
     * @return
     */
    @Override
    public Author getAuthorByName(String authorname) {

        return authorDao.selectByAuthorName(authorname);
    }

    /**
     * 修改用户信息
     * @param author
     * @return
     */
    @Override
    public Integer updateAuthor(Author author) {
        int res;
        int i = authorDao.updateByPrimaryKeySelective(author);
        if(i==1){//表示增加用户成功
            res=1;
        }else{
            res=0;
        }
        return res;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteId(Integer id) {
        if (authorDao.deleteByPrimaryKey(id) == 1) {//成功
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 作者总量
     * @return
     */
    @Override
    public int getAuthorSum() {
        return authorDao.selectAuthorSum();
    }
}
