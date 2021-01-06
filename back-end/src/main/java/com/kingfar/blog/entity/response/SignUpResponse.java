package com.kingfar.blog.entity.response;

import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class SignUpResponse extends Response {
    public static final Response successfulResp = new SignUpResponse(0, "Successfully signed up!");
    public static final Response failResp = new SignUpResponse(1, "Failed to sign up, please change your username.");

    public SignUpResponse(int code, String msg) {
        super(code, msg);
    }

    @Override
    protected int groupCode() {
        return 2;
    }
}
