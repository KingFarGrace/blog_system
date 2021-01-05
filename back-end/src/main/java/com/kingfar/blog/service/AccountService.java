package com.kingfar.blog.service;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;

/**
 * @author ZHANGKAIHENG
 */
public interface AccountService {
    UserVerifyData verify(String username);

    UserLoginData getLoginData(String username);

    boolean createNewUser(String username, String password);

    void updatePublicInfo(UserLoginData userLoginData);

    void updatePassword(String username, String password);

    boolean passwordValidator(String oldPwd, String newPwd);
}
