package com.kingfar.blog.entity.response;

import com.kingfar.blog.entity.UserLoginData;
import lombok.Data;

/**
 * @author ZHANGKAIHENG
 */

@Data
public class LoginResponse extends Response {
    private UserLoginData userLoginData;

    @Override
    protected int groupCode() {
        return 1;
    }

    public LoginResponse(int code, String msg, UserLoginData userLoginData) {
        super(code, msg);
        this.userLoginData = userLoginData;
    }
}
