package com.kingfar.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UserLoginData implements Serializable {
    private long uid;
    private String username;
    private String mail;
    private int age;
    private String sex;
    private String signature;
}
