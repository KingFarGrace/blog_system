package com.kingfar.blog.entity.response;

import com.kingfar.blog.entity.ArticleData;
import com.kingfar.blog.entity.buffer.ArticleBuffer;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class ArticleResponse extends Response {
    Map<String, Object> respMap;

    public static Map<String, Object> emptyMap = new HashMap<>();

    static {
        ArticleBuffer buffer = ArticleBuffer.getInstance();
        emptyMap.put("buffer-length", 0);
        emptyMap.put("page-length", buffer.getPageLen());
        emptyMap.put("current-page", 1);
        emptyMap.put("articles", null);
    }

    @Override
    protected int groupCode() {
        return 3;
    }

    public ArticleResponse(int code, String msg, Map<String, Object> respMap) {
        super(code, msg);

        this.respMap = respMap;
    }
}
