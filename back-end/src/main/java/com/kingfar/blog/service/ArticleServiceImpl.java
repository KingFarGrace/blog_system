package com.kingfar.blog.service;

import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;
import com.kingfar.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
