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

    void deleteHistoryBlog(int bid);

    List<ArticleData> getHistory(String username);

    void saveAsDraft(ArticleData article);

    void deleteDraft(int bid);

    List<ArticleData> getDrafts(String username);
}
