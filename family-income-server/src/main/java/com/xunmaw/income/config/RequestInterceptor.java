package com.xunmaw.income.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.xunmaw.income.utils.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.xunmaw.income.utils.TokenRst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        if("/user".equals(request.getServletPath()) && "POST".equals(request.getMethod())){
            //放行 用户注册
            return true;
        }
        String authorization = request.getHeader("authorization");
        if(authorization == null){
            // token 不存在返回错误信息。
            TokenRst.Rst(response, "token 不存在");
            return false;
        }
        String token = authorization;
        try {
            if (TokenUtils.isTokenExp(token)) {
                // token 过期
                TokenRst.Rst(response, "token 过期了");
                return false;
            }
        }catch (JWTDecodeException | IllegalArgumentException | SignatureVerificationException e){
            // token 不合法
            TokenRst.Rst(response, "token 不合法");
            return false;
        }

        Map<String, Object> params = TokenUtils.getParams(token);
        String username = String.valueOf(params.get("username"));
        request.setAttribute("username", username);
        return true;
    }
}
