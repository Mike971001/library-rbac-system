package com.demo.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:58:05
 * @description: Jwt工具类
 */
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 生成token
     * @param username 用户名
     * @return
     */
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * 从token 中获取用户名
     * @param authToken 令牌
     * @return
     */
    public String getUsernameFromJwtToken(String authToken){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
                .getBody().getSubject();
    }

    /**
     * 校验token
     * @param authToken 令牌
     * @return
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | IllegalArgumentException | UnsupportedJwtException e) {
            //e.printStackTrace();
            log.info("解析异常: 令牌 {}", authToken);
        }
        return false;
    }
}
