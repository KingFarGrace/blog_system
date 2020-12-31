package com.kingfar.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void testSelectAll() {
        System.out.println(articleMapper.selectAll());
    }

    @Test
    void testCount() {
        System.out.println(articleMapper.countArticles());
    }
}