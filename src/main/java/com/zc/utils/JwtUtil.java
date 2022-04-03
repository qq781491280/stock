package com.zc.utils;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zc.domian.User;
import com.zc.service.ex.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class JwtUtil {

    private static String  TOKEN = "token!Q@W3e4r";


    public static String getToken(Integer userid,String username) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);
        String token= JWT.create()
                .withExpiresAt(instance.getTime())
                .withClaim("userid",userid)
                .withClaim("username",username)
                .sign(Algorithm.HMAC256(TOKEN));
        return token;
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token)  {
        if(StringUtils.isEmpty(token)){
            throw new UserNotFoundException("token不能为空");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(TOKEN)).build();
        return build.verify(token);
    }

//    public static  DecodedJWT getToken(String token){
//
//        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
//    }

}


