package com.kingfar.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UserVerifyData implements Serializable {
    private String username;
    private String password;
    private String perms;
}
