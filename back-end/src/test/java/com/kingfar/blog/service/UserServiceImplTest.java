package com.kingfar.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void testUserService() {
        System.out.println(userService.verify("ZHANG"));
        System.out.println(userService.getLoginData("ZHANG"));
    }

    @Test
    void testSignUp() {
        assertEquals(false, userService.createNewUser("WANG", "123456"));
    }
}