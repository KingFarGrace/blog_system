package com.kingfar.blog.service;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;

/**
 * @author ZHANGKAIHENG
 */
public interface UserService {
    public UserVerifyData verify(String username);
    public UserLoginData getLoginData(String username);
    public boolean createNewUser(String username, String password);
}
