package com.kingfar.blog.intercepetor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.entity.response.TokenResponse;
import com.kingfar.blog.util.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZHANGKAIHENG
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println(token);
        String json = null;
        response.setContentType("application/json; charset=UTF-8");
        // TODO attach TokenResponse to Response (revise those prints)
        try {
            JwtUtils.verify(token);
            json = new ObjectMapper().writeValueAsString(TokenResponse.validTokenResp);
            return true;
        } catch (SignatureVerificationException sve) {
            sve.printStackTrace();
            json = new ObjectMapper().writeValueAsString(TokenResponse.invalidSignResp);
        } catch (TokenExpiredException tee) {
            tee.printStackTrace();
            json = new ObjectMapper().writeValueAsString(TokenResponse.tokenExpiredResp);
        } catch (AlgorithmMismatchException ame) {
            ame.printStackTrace();
            json = new ObjectMapper().writeValueAsString(TokenResponse.AlgMismatchedResp);
        } catch (Exception e) {
            e.printStackTrace();
            json = new ObjectMapper().writeValueAsString(TokenResponse.invalidTokenResp);
        }
        response.getWriter().println(json);
        return false;
    }
}
