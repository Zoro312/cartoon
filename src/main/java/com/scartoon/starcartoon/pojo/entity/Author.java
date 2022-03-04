package com.scartoon.starcartoon.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * author
 * @author 
 */
@Data
public class Author implements Serializable {
    private Integer authorid;

    private String authorname;

    private String password;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phoneNumber;

    private static final long serialVersionUID = 1L;

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}