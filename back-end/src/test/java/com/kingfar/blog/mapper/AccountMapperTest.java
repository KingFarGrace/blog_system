package com.kingfar.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;

    @Test
    void TestMapper() {
        System.out.println(accountMapper.selectByUsername("ZHANG"));
    }

    @Test
    void TestSignUp() {

    }

}