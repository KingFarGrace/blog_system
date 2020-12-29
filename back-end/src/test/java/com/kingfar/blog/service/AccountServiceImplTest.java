package com.kingfar.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    AccountService accountService;

    @Test
    void testUserService() {
        System.out.println(accountService.verify("ZHANG"));
        System.out.println(accountService.getLoginData("ZHANG"));
    }

    @Test
    void testSignUp() {
        System.out.println(accountService.createNewUser("ZHANG", "123456"));
    }
}