package com.kingfar.blog.controller;

import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.entity.response.LoginResponse;
import com.kingfar.blog.entity.response.SignUpResponse;
import com.kingfar.blog.service.UserService;
import com.kingfar.blog.entity.response.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZHANGKAIHENG
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/verify")
    Response verify(@RequestBody UserVerifyData data) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(data.getUsername(), data.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            // no user matched, return code 101
            return new LoginResponse(1, "No such user!", null);
        } catch (IncorrectCredentialsException ice) {
            // wrong password, return code 102
            return new LoginResponse(2, "Wrong password!", null);
        }
        // success, return code 100
        return new LoginResponse(0, "Successfully login!", userService.getLoginData(data.getUsername()));
    }

    @PostMapping("/signUp")
    Response signUp(String username, String password) {
        if(userService.createNewUser(username, password) == true) {
            // success, return code 200
            return SignUpResponse.successfulResp;
        } else {
            // fail, return code 201
            return SignUpResponse.failResp;
        }
    }

    @PostMapping("/logout")
    Response logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return null;
    }
}
