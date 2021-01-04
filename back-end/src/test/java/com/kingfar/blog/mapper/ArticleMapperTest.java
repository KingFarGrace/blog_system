package com.kingfar.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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

    @Test
    void testInsert() {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(articleMapper.insertArticle("Test submit", "can I submit this article?", "ZHANG", date));
    }

    @Test
    void testSearch() {
        System.out.println(articleMapper.searchByTitle("a"));
    }
}