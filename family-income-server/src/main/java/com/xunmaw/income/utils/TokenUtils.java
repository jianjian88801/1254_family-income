package com.xunmaw.income.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class TokenUtils {
    private final static String KEY = "xiaoran666";
    static Algorithm algorithm = Algorithm.HMAC256(KEY);
    private final static String ISSUER = "XiaoRan";
    /**
     * 根据 params 和 过期时间生成 token
     * @param params 参数
     * @param exp 过期时间 秒
     * @return token
     */
    public static String createToken(Map<String, String> params, Long exp){
        JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER);
        for(String key : params.keySet()){
            String val = params.get(key);
            builder.withClaim(key, val);
        }
        Date expDate = new Date(System.currentTimeMillis() + exp * 1000);
        builder.withExpiresAt(expDate);
        return builder.sign(algorithm);
    }

    /**
     * 解析 token
     * @param token token
     * @return DecodedJWT
     */
    public static DecodedJWT parseToken(String token){
        DecodedJWT decodedJWT = null;
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .build();
        try {
            decodedJWT = jwtVerifier.verify(token);
        }catch (TokenExpiredException e){
            System.out.println("token 过期");
        }
        return decodedJWT;
    }

    /**
     * 检测 token是否过期
     * @param token token
     * @return boolean
     */
    public static boolean isTokenExp(String token){
        return parseToken(token) == null;
    }

    /**
     * 返回 token 中的参数
     * @param token token
     * @return 参数
     */
    public static Map<String, Object> getParams(String token){
        DecodedJWT decodedJWT = parseToken(token);
        String payload = decodedJWT.getPayload();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(payload);
        String paramsString = new String(decode);
        JSONObject jsonObject = JSON.parseObject(paramsString);
        return jsonObject.getInnerMap();
    }
}
