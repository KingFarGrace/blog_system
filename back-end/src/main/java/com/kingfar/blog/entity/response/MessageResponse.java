package com.kingfar.blog.entity.response;

import lombok.Data;

import java.util.Map;

/**
 * @author WUHAIYUAN
 */
@Data
public class MessageResponse extends Response {

    private Map<String, Object> respMap;

    public MessageResponse(int code, String msg, Map<String, Object> respMap) {
        super(code, msg);
        this.respMap = respMap;
    }

    @Override
    protected int groupCode() {
        return 5;
    }
}
