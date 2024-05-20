package com.demo.service;

import com.demo.entity.LibraryUser;
import com.demo.entity.enums.LibraryStatus;
import com.demo.mapper.LibraryUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 16:21:29
 * @description: 用户自定义 业务接口层
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private LibraryUserMapper userMapper;

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 通过用户名查询用户
        LibraryUser user = userMapper.findUserByUsername(username);

        // 如果用户名包含 @ 查询用户
        if (username.contains("@")) {
            user = userMapper.findUserByEmail(username);
        }

        if (user == null) {
            log.info("CustomUserDetailsService 查询用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }

        // 用户状态
        String userStatus = user.getUserStatus();

        if (userStatus.equals(LibraryStatus.NORMAL.getStatusCode())) {
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }


        if (userStatus.equals(LibraryStatus.DISABLE.getStatusCode())) {
            log.info("CustomUserDetailsService 用户 {} 已停用", username);
            throw new RuntimeException(LibraryStatus.DISABLE.getStatusDesc());
        }

        if (userStatus.equals(LibraryStatus.DELETED.getStatusCode())) {
            log.info("CustomUserDetailsService 用户 {} 已被删除了", username);
            throw new RuntimeException(LibraryStatus.DELETED.getStatusDesc());
        }

        throw new RuntimeException("账号异常,请联系管理员");
    }
}
