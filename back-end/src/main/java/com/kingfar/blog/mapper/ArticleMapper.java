package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.ArticleData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface ArticleMapper {
    public List<ArticleData> selectAll();
    public int countArticles();
}
