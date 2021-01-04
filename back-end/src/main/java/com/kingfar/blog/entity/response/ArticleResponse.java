package com.kingfar.blog.entity.response;

import com.kingfar.blog.entity.ArticleData;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class ArticleResponse extends Response {
    Map<String, Object> respMap;

    @Override
    protected int groupCode() {
        return 3;
    }

    public ArticleResponse(int code, String msg, Map<String, Object> respMap) {
        super(code, msg);

        this.respMap = respMap;
    }
}
