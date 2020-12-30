package com.kingfar.blog.util;

import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
public class ArticleUtils {

    public static ArticleBuffer getBuffer() {
        return ArticleBuffer.getInstance();
    }

    public static List<ArticleData> getPage(int currentPage) {
        // TODO 前端设置页面加载完毕自动请求页面数据（缓冲区容量，页数等等）
        ArticleBuffer buffer = ArticleBuffer.getInstance();
        buffer.setCurrentPage(currentPage);
        buffer.setCurrentIndexOfArticles((currentPage - 1) * buffer.getPageLen());
        int bound = Math.min(buffer.getCurrentIndexOfArticles() + buffer.getPageLen(), buffer.getBufferLen());
        List<ArticleData> articleList = buffer.getArticles();
        List<ArticleData> page = new ArrayList<>();
        for (int i = buffer.getCurrentIndexOfArticles(); i < bound; i++) {
            page.add(articleList.get(i));
        }
        return page;
    }

}
