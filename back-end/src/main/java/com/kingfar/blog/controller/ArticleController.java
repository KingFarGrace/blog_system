package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;
import com.kingfar.blog.service.ArticleService;
import com.kingfar.blog.util.ArticleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    Map load(@RequestBody JSONObject jsonObject) {
        String pageIndex = jsonObject.getString("pageIndex");
        int currentPage = Integer.parseInt(pageIndex);
        ArticleBuffer buffer = ArticleUtils.getBuffer();
        articleService.getResource();
        Map respMap = new HashMap(4);
        respMap.put("buffer-length", buffer.getBufferLen());
        respMap.put("page-length", buffer.getPageLen());
        respMap.put("current-page", buffer.getCurrentPage());
        respMap.put("articles", ArticleUtils.getPage(currentPage));
        return respMap;
    }

    @PostMapping("/submit")
    Map submit(@RequestBody ArticleData article) {
        Map<String, String> reapMap = new HashMap<>(1);
        try {
            articleService.submit(article);
        } catch (Exception e) {
            e.printStackTrace();
            reapMap.put("msg", "failed to submit");
        }
        reapMap.put("msg", "success");
        return reapMap;
    }
}
