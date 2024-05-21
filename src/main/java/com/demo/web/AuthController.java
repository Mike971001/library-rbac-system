package com.demo.web;

import com.demo.entity.LibraryUser;
import com.demo.entity.request.LoginUser;
import com.demo.entity.request.RegisterUser;
import com.demo.service.LibraryUserService;
import com.demo.utils.JwtUtils;
import com.demo.utils.SaltGenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户注册
     * @param registerUser
     * @return
     */
    /*@PostMapping(value = "/register")
    public String register(@RequestBody RegisterUser registerUser){
        LibraryUser user = new LibraryUser();
        user.setUsername(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        userService.saveLibraryUser(user);
        return "User registered successfully, UserId: " +user.getUserId();
    }*/

    /**
     * 用户注册
     * @param registerUser
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@RequestBody RegisterUser registerUser){
        LibraryUser user = userService.RegisterUser(registerUser);
        return "User registered successfully, UserId: " +user.getUserId();
    }

    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @PostMapping(value = "/login")
    public String login(@RequestBody LoginUser loginUser) {
        // 通过用户名和密码获取 ===> 认证 token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());

        Authentication authentication = authenticationManager
                .authenticate(authenticationToken);

        return jwtUtils.generateJwtToken(authentication.getName());
    }

}
