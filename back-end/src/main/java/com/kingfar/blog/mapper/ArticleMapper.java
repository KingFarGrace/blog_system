package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.ArticleData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface ArticleMapper {
    List<ArticleData> selectAll();

    int countArticles();

    int insertArticle(@Param("title") String title, @Param("content") String content, @Param("author") String author, @Param("ctime") Date ctime);

    List<ArticleData> searchByTitle(String title);

    List<ArticleData> searchByUsername(String username);

    int deleteByTitle(String title);
}
