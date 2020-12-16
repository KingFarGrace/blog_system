package com.kingfar.blog.entity.response;

import lombok.NoArgsConstructor;

/**
 * @author ZHANGKAIHENG
 */
@NoArgsConstructor
public class SignUpResponse extends Response {
    public static final Response successfulResp = new SignUpResponse(0, "Successfully signed up!");
    public static final Response failResp = new SignUpResponse(1, "Failed to sign up, please change your username.");

    @Override
    protected int groupCode() {
        return 2;
    }

    public SignUpResponse(int code, String msg) {
        super(code, msg);
    }
}
