package com.kingfar.blog.entity.buffer;

import com.kingfar.blog.entity.ArticleData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHANGKAIHENG
 */

@Getter
@Setter
public class ArticleBuffer {
    private int bufferLen;
    private int pageLen;
    private List<ArticleData> articles;
    private int currentIndexOfArticles;
    private int currentPage;
    private static ArticleBuffer buffer;

    private ArticleBuffer() {
        this.bufferLen = 0;
        this.pageLen = 5;
        this.articles = new ArrayList<>();
        this.currentIndexOfArticles = 0;
        this.currentPage = 1;
    }

    public static ArticleBuffer getInstance() {
        if(buffer == null) {
            buffer = new ArticleBuffer();
        }
        return buffer;
    }
}
