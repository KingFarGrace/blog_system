package com.kingfar.blog.service;

import com.kingfar.blog.entity.ArticleData;

/**
 * @author ZHANGKAIHENG
 */
public interface ArticleService {
    void getResource();

    void submit(ArticleData article);
}
