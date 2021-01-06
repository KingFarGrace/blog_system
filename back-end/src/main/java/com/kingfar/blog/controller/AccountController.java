package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.entity.response.LoginResponse;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.entity.response.SignUpResponse;
import com.kingfar.blog.entity.response.UpdateResponse;
import com.kingfar.blog.service.AccountService;
import com.kingfar.blog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author ZHANGKAIHENG
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/verify")
    Response verify(HttpServletResponse response, @RequestBody UserVerifyData data) {
        response.setHeader("Access-Control-Expose-Headers", "status, token");
        UserVerifyData verifyData = accountService.verify(data.getUsername());
        if (verifyData != null) {
            if (accountService.passwordValidator(verifyData.getPassword(), data.getPassword()) == true) {
                String token = JwtUtils.sign(data.getUsername());
                response.setHeader("token", token);
                return new LoginResponse(0, "Successfully login!", accountService.getLoginData(data.getUsername()));
            } else {
                return new LoginResponse(2, "wrong password", null);
            }
        } else {
            return new LoginResponse(1, "no such user!", null);
        }
    }

    @PostMapping("/signUp")
    Response signUp(String username, String password) {
        if (accountService.createNewUser(username, password) == true) {
            try {
                accountService.createDefaultGroup(username);
            } catch (Exception e) {
                e.printStackTrace();
                return SignUpResponse.failResp;
            }
            return SignUpResponse.successfulResp;
        } else {
            // fail, return code 201
            return SignUpResponse.failResp;
        }
    }

    @PostMapping("/updatePublicInfo")
    Response updatePublicInfo(@RequestBody UserLoginData userLoginData) {
        try {
            accountService.updatePublicInfo(userLoginData);
        } catch (DuplicateKeyException dke) {
            dke.printStackTrace();
            return UpdateResponse.UsernameDuplicatedResp;
        } catch (Exception e) {
            return UpdateResponse.failedToUpdatePublicInfoResp;
        }
        return UpdateResponse.successResp;
    }

    @PostMapping("/updatePassword")
    Response updatePassword(@RequestParam String username, @RequestParam String oldPwd, @RequestParam String newPwd) {
        if (accountService.passwordValidator(oldPwd, newPwd) == true) {
            return UpdateResponse.samePasswordResp;
        } else if (accountService.passwordValidator(oldPwd, accountService.verify(username).getPassword()) == false) {
            return UpdateResponse.wrongPasswordResp;
        } else {
            try {
                accountService.updatePassword(username, newPwd);
            } catch (Exception e) {
                e.printStackTrace();
                return UpdateResponse.failedToUpdatePasswordResp;
            }
            return UpdateResponse.successResp;
        }
    }

    @PostMapping("/getUser")
    Response getUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        UserLoginData user;
        try {
            user = accountService.getLoginData(username);
            if(user.getUid() == 0) {
                return new LoginResponse(3, "cannot find user:" + username, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponse(4, "failed to get info of user:" + username, null);
        }
        return new LoginResponse(0, "success", user);
    }
}
