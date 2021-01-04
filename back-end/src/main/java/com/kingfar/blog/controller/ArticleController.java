package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;
import com.kingfar.blog.entity.response.ArticleResponse;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.service.ArticleService;
import com.kingfar.blog.util.ArticleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHANGKAIHENG
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/load")
    Response load(@RequestBody JSONObject jsonObject) {
        String pageIndex = jsonObject.getString("pageIndex");
        int currentPage = Integer.parseInt(pageIndex);
        ArticleBuffer buffer = ArticleUtils.getBuffer();
        try {
            articleService.getResource();
        } catch (Exception e) {
            return new ArticleResponse(1, "failed to load resource", null);
        }
        Map respMap = new HashMap(4);
        respMap.put("buffer-length", buffer.getBufferLen());
        respMap.put("page-length", buffer.getPageLen());
        respMap.put("current-page", buffer.getCurrentPage());
        respMap.put("articles", ArticleUtils.getPage(currentPage));
        return new ArticleResponse(0, "success", respMap);
    }

    @PostMapping("/submit")
    ArticleResponse submit(@RequestBody ArticleData article) {
        try {
            articleService.submit(article);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(3, "failed to submit", null);
        }
        return new ArticleResponse(0, "success!", null);
    }

    @PostMapping("/search")
    Response search(@RequestBody JSONObject jsonObject) {
        String title = jsonObject.getString("key");
        List<ArticleData> articles;
        try {
            articles = articleService.search(title);
        } catch (Exception e) {
            return new ArticleResponse(2, "no results matched", null);
        }
        ArticleBuffer buffer = ArticleUtils.getBuffer();
        Map respMap = new HashMap(4);
        respMap.put("buffer-length", articles.size());
        respMap.put("page-length", buffer.getPageLen());
        respMap.put("current-page", 1);
        respMap.put("articles", articles);
        return new ArticleResponse(0, "success!", respMap);
    }
}
