package com.kingfar.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void TestMapper() {
        System.out.println(userMapper.selectByUsername("ZHANG"));
    }

    @Test
    void TestSignUp() {
        System.out.println(userMapper.setSignUpInfo("WANG", "123456"));
    }

}