package com.demo.service.impl;

import com.demo.entity.LibraryUser;
import com.demo.mapper.LibraryUserMapper;
import com.demo.service.LibraryUserService;
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
}
