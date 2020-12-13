package com.kingfar.blog.entity;

import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UserLoginData {
    private String uid;
    private String username;
    private String mail;
    private int age;
    private String sex;
    private String signature;
}
