package com.kingfar.blog.service;

import com.kingfar.blog.entity.ArticleData;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
public interface ArticleService {
    void getResource();

    void submit(ArticleData article);

    List<ArticleData> search(String title);
}
