package com.demo.security;

import com.demo.service.CustomUserDetailsService;
import com.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 16:35:36
 * @description: Jwt认证过滤器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHear = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHear != null && authorizationHear.startsWith("Bearer ")){
            // 获取 jwt 的内容部分
            jwt = authorizationHear.substring(7);
            // 从令牌中获取用户名
            username = jwtUtils.getUsernameFromJwtToken(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // 通过用户名查询用户信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 检验 token是否合法
            if (jwtUtils.validateJwtToken(jwt)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // 执行过滤器链
        filterChain.doFilter(request,response);
    }
}
