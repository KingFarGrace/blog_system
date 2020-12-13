package com.kingfar.blog.entity;

import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UserVerifyData {
    private String username;
    private String password;
    private String perms;
}
