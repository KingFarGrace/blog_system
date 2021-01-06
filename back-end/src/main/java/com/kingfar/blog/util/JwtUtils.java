package com.kingfar.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author ZHANGKAIHENG
 */
public class JwtUtils {
    public static final String salt = "ASF@HTV^KRP";
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    public static final Algorithm ALG = Algorithm.HMAC256(salt);

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALG).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String sign(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(ALG);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String token) {
        if (token == null || "".equals(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
