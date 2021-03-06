package com.kingfar.blog.service;

import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;
import com.kingfar.blog.mapper.ArticleMapper;
import com.kingfar.blog.util.ArticleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void getResource() {
        ArticleBuffer buffer = ArticleBuffer.getInstance();
        buffer.setArticles(articleMapper.selectAll());
        buffer.setBufferLen(articleMapper.countArticles());
    }

    @Override
    public void submit(ArticleData article) {
        Date date = new Date(System.currentTimeMillis());
        article.setCtime(date);
        ArticleUtils.submit(article);
        articleMapper.insertArticle(article.getTitle(), article.getContent(), article.getAuthor(), date);
    }

    @Override
    public List<ArticleData> search(String title) {
        return articleMapper.searchByTitle(title);
    }

    @Override
    public void deleteHistoryBlog(int bid) {
        articleMapper.deleteBlog(bid);
    }

    @Override
    public List<ArticleData> getHistory(String username) {
        return articleMapper.searchByUsername(username);
    }

    @Override
    public void saveAsDraft(ArticleData article) {
        Date date = new Date(System.currentTimeMillis());
        article.setCtime(date);
        System.out.println(article.getBid());
        if (articleMapper.findDraft(article.getBid()) != null) {
            articleMapper.coverDraft(article.getBid(), article.getTitle(), article.getContent(), article.getAuthor(), date);
        } else {
            articleMapper.saveDraft(article.getTitle(), article.getContent(), article.getAuthor(), date);
        }
    }

    @Override
    public void deleteDraft(int bid) {
        articleMapper.deleteDraft(bid);
    }

    @Override
    public List<ArticleData> getDrafts(String username) {
        return articleMapper.getDraftsByUsername(username);
    }

    @Override
    public void addFavor(String username, int bid) {
        articleMapper.addFavor(username, bid);
    }

    @Override
    public void deleteFavor(String username, int bid) {
        articleMapper.deleteFavor(username, bid);
    }

    @Override
    public List<ArticleData> getFavors(String username) {
        return articleMapper.getAllFavor(username);
    }
}
