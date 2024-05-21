package com.demo.web;

import com.demo.constant.LibrarySystemConstant;
import com.demo.entity.LibraryUser;
import com.demo.entity.dto.ApiResponse;
import com.demo.entity.request.LoginUser;
import com.demo.entity.request.RegisterUser;
import com.demo.service.LibraryUserService;
import com.demo.utils.JwtUtils;
import com.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 16:54:07
 * @description: 认证前端控制器
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LibraryUserService userService;

    /**
     * 用户注册
     * @param registerUser 注册的用户
     * @return
     */
    @PostMapping(value = "/register")
    public ApiResponse register(@RequestBody RegisterUser registerUser){
        return userService.registerUser(registerUser);
    }

    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @PostMapping(value = "/login")
    public ApiResponse login(@RequestBody LoginUser loginUser) {
        // 通过用户名和密码获取 ===> 认证 token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());

        Authentication authentication = authenticationManager
                .authenticate(authenticationToken);

        // 拼接token前缀
        String jwtToken = LibrarySystemConstant.JWT_TOKEN_PREFIX +jwtUtils.generateJwtToken(authentication.getName());

        return ResponseUtil.success("登录成功",jwtToken);
    }

}
