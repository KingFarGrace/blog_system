package com.kingfar.blog.service;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZHANGKAIHENG
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserVerifyData verify(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public UserLoginData getLoginData(String username) {
        return userMapper.queryLoginData(username);
    }

    @Override
    public boolean createNewUser(String username, String password) {
        try {
            userMapper.setSignUpInfo(username, password);
        } catch (Exception e) {
            /**
             * fail to sign up because
             * 1. wrong args type
             * 2. unique username
             */
            return false;
        }
        return true;
    }
}
