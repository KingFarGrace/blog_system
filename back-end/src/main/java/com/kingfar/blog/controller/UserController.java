package com.kingfar.blog.controller;

import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.entity.response.LoginResponse;
import com.kingfar.blog.entity.response.SignUpResponse;
import com.kingfar.blog.service.UserService;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

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
    Response verify(HttpServletResponse response, @RequestBody UserVerifyData data) {
        response.setHeader("Access-Control-Expose-Headers", "status, token");
        UserVerifyData verifyData = userService.verify(data.getUsername());
        if(verifyData != null){
            if (Objects.equals(verifyData.getPassword(), data.getPassword())){
                String token = JwtUtils.sign(data.getUsername());
                response.setHeader("token", token);
                return new LoginResponse(0, "Successfully login!", userService.getLoginData(data.getUsername()));
            } else {
                return new LoginResponse(2, "wrong password", null);
            }
        } else {
            return new LoginResponse(1, "no such user!", null);
        }
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
}
