package com.kingfar.blog.entity.response;

import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class TokenResponse extends Response {
    public static final Response validTokenResp = new TokenResponse(0, "valid token");
    public static final Response invalidSignResp = new TokenResponse(1, "invalid sign");
    public static final Response tokenExpiredResp = new TokenResponse(2, "token expired");
    public static final Response AlgMismatchedResp = new TokenResponse(3, "sign algorithm mismatched");
    public static final Response invalidTokenResp = new TokenResponse(4, "invalid token");

    public TokenResponse(int code, String msg) {
        super(code, msg);
    }

    @Override
    protected int groupCode() {
        return 3;
    }
}
