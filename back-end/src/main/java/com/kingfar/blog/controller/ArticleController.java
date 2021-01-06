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
 * <p>
 * ArticleResponse code
 * 300: success
 * 301: load error -> failed to load resource
 * 302: submit error -> failed to submit
 * 303: search error -> no results matched
 * 304: search error -> failed to search article
 * 305: getHistoryBlog error -> failed to load history blog
 * 306: deleteHistoryBlog error -> failed to delete history blog
 * 307: saveAsDraft error -> failed to save
 * 308: deleteDraft error -> failed to delete
 * 309: getDrafts error -> failed to load drafts
 * 310: addFavor error -> failed to add favor
 * 311: deleteFavor error -> failed to delete favor
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
            return new ArticleResponse(1, "failed to load resource", ArticleResponse.emptyMap);
        }
        ArticleUtils.setCurrentPage(currentPage);
        Map<String, Object> respMap = new HashMap<>(4);
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
            return new ArticleResponse(2, "failed to submit", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success", ArticleResponse.emptyMap);
    }

    @PostMapping("/search")
    Response search(@RequestBody JSONObject jsonObject) {
        String title = jsonObject.getString("key");
        List<ArticleData> articles;
        try {
            articles = articleService.search(title);
            if (articles.size() == 0) {
                return new ArticleResponse(3, "no results matched", ArticleResponse.emptyMap);
            }
        } catch (Exception e) {
            return new ArticleResponse(4, "failed to search article", ArticleResponse.emptyMap);
        }
        ArticleBuffer buffer = ArticleUtils.getBuffer();
        Map<String, Object> respMap = new HashMap<>(4);
        respMap.put("buffer-length", articles.size());
        respMap.put("page-length", buffer.getPageLen());
        respMap.put("current-page", 1);
        respMap.put("articles", articles);
        return new ArticleResponse(0, "success", respMap);
    }

    @PostMapping("/deleteHistoryBlog")
    Response deleteHistoryBlog(@RequestBody JSONObject jsonObject) {
        int bid = jsonObject.getInteger("bid");
        try {
            articleService.deleteHistoryBlog(bid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(6, "failed to delete history blog", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success", ArticleResponse.emptyMap);
    }

    @PostMapping("/getHistoryBlog")
    Response getHistoryBlog(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        List<ArticleData> articles;
        try {
            articles = articleService.getHistory(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(5, "failed to load history blogs", ArticleResponse.emptyMap);
        }
        Map<String, Object> respMap = new HashMap<>(4);
        respMap.put("buffer-length", articles.size());
        respMap.put("page-length", 10);
        respMap.put("current-page", 1);
        respMap.put("articles", articles);
        return new ArticleResponse(0, "success", respMap);
    }

    @PostMapping("/saveAsDraft")
    Response saveAsDraft(@RequestBody ArticleData article) {
        try {
            articleService.saveAsDraft(article);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(7, "failed to save", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success!", ArticleResponse.emptyMap);
    }

    @PostMapping("/deleteDraft")
    Response deleteDraft(@RequestBody JSONObject jsonObject) {
        int bid = jsonObject.getInteger("bid");
        try {
            articleService.deleteDraft(bid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(8, "failed to delete", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success", ArticleResponse.emptyMap);
    }

    @PostMapping("/getDrafts")
    Response getDrafts(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        List<ArticleData> articles;
        try {
            articles = articleService.getDrafts(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(9, "failed to load drafts", ArticleResponse.emptyMap);
        }
        Map<String, Object> respMap = new HashMap<>(4);
        respMap.put("buffer-length", articles.size());
        respMap.put("page-length", 10);
        respMap.put("current-page", 1);
        respMap.put("articles", articles);
        return new ArticleResponse(0, "success", respMap);
    }

    @PostMapping("/addFavor")
    Response addFavor(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int bid = jsonObject.getInteger("bid");
        try {
            articleService.addFavor(username, bid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(10, "failed to add favor", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success", ArticleResponse.emptyMap);
    }

    @PostMapping("/deleteFavor")
    Response deleteFavor(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int bid = jsonObject.getInteger("bid");
        try {
            articleService.deleteFavor(username, bid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(11, "failed to delete favor", ArticleResponse.emptyMap);
        }
        return new ArticleResponse(0, "success", ArticleResponse.emptyMap);
    }

    @PostMapping("/getFavors")
    Response getFavors(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        List<ArticleData> articles;
        try {
            articles = articleService.getFavors(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArticleResponse(12, "failed to get favors", ArticleResponse.emptyMap);
        }
        Map<String, Object> respMap = new HashMap<>(4);
        respMap.put("buffer-length", articles.size());
        respMap.put("page-length", 10);
        respMap.put("current-page", 1);
        respMap.put("articles", articles);
        return new ArticleResponse(0, "success", respMap);
    }
}
