package com.zc.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zc.utils.JsonResult;
import com.zc.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(400);
        //获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            JwtUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e){
            jsonResult.setMessage("无效签名");
        } catch (AlgorithmMismatchException e){
            jsonResult.setMessage("token算法不一致");
        } catch (TokenExpiredException e){
            jsonResult.setMessage("token过期");
        } catch (Exception e){
            jsonResult.setMessage("您还未登录");
        }
        String json = new ObjectMapper().writeValueAsString(jsonResult);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}