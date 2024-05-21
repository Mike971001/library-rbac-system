package com.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.demo.entity.LibraryUser;
import com.demo.entity.dto.ApiResponse;
import com.demo.entity.enums.ApiResponseEnums;
import com.demo.entity.request.RegisterUser;
import com.demo.mapper.LibraryUserMapper;
import com.demo.security.SaltedPasswordEncoder;
import com.demo.service.LibraryUserService;
import com.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 15:39:32
 * @description: 图书馆 用户 服务接口层
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
public class LibraryUserServiceImpl implements LibraryUserService {

    @Autowired
    private LibraryUserMapper userMapper;

    @Autowired
    private SaltedPasswordEncoder passwordEncoder;

    @Override
    public LibraryUser findUserByUsername(String username) {
        LibraryUser user = userMapper.findUserByUsername(username);
        return user;
    }

    @Override
    public LibraryUser findUserByEmail(String email) {
        LibraryUser user = userMapper.findUserByEmail(email);
        return user;
    }

    @Override
    public void saveLibraryUser(LibraryUser user) {
        int i = userMapper.saveLibraryUser(user);
    }

    @Override
    public LibraryUser RegisterUser(RegisterUser registerUser) {

        //加密后的密码
        String encodePassword = passwordEncoder.encode(registerUser.getPassword());
        // 提取盐和加密后的密码
        String[] parts = encodePassword.split(":");
        // Base64加密后的盐
        String base64Salt = parts[0];
        // hash加密后的盐
        String hashPassword = parts[1];

        LibraryUser user = new LibraryUser();
        user.setUsername(registerUser.getUsername());
        user.setPassword(hashPassword);
        user.setSalt(base64Salt);
        user.setEmail(registerUser.getEmail());
        userMapper.saveLibraryUser(user);

        return user;
    }

    @Override
    public ApiResponse registerUser(RegisterUser registerUser) {
        String msg = "";

        if (StrUtil.isBlank(registerUser.getUsername())){
            msg = msg + "用户名不能为空";
        }

        if (StrUtil.isBlank(registerUser.getPassword())){
            msg = msg + "密码不能为空";
        }

        if (StrUtil.isNotBlank(msg)){
            return ResponseUtil.error(ApiResponseEnums.GENERAL_EXCEPTION.getResponseCode(),msg);
        }


        //加密后的密码
        String encodePassword = passwordEncoder.encode(registerUser.getPassword());
        // 提取盐和加密后的密码
        String[] parts = encodePassword.split(":");
        // Base64加密后的盐
        String base64Salt = parts[0];
        // hash加密后的盐
        String hashPassword = parts[1];

        LibraryUser user = new LibraryUser();
        user.setUsername(registerUser.getUsername());
        user.setPassword(hashPassword);
        user.setSalt(base64Salt);
        user.setEmail(registerUser.getEmail());

        int sameNameCount = userMapper.checkLibraryUserSameName(user);
        if (sameNameCount > 0){
            return ResponseUtil.error(ApiResponseEnums.USER_NAME_SAME);
        }

        if (StrUtil.isNotBlank(user.getEmail())){
            int sameEmailCount =  userMapper.checkLibraryUserSameEmail(user);
            if (sameEmailCount >0 ){
                return ResponseUtil.error(ApiResponseEnums.USER_EMAIL_SAME);
            }
        }
        // 校验之后添加用户到数据库
        userMapper.saveLibraryUser(user);
        return ResponseUtil.success("用户注册成功,新的用户id为: " +user.getUserId());
    }
}
