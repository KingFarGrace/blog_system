package com.kingfar.blog.service;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ZHANGKAIHENG
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public UserVerifyData verify(String username) {
        return accountMapper.selectByUsername(username);
    }

    @Override
    public UserLoginData getLoginData(String username) {
        return accountMapper.queryLoginData(username);
    }

    @Override
    public boolean createNewUser(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis());
            accountMapper.setSignUpInfo(username, password, date);
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
