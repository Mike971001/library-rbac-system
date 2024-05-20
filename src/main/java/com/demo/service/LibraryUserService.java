package com.demo.service;

import com.demo.entity.LibraryUser;

import java.util.List;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 15:38:11
 * @description: 图书馆 用户 服务层
 */
public interface LibraryUserService {

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return
     */
    LibraryUser findUserByUsername(String username);

    /**
     * 通过邮箱查询用户
     * @param email 邮箱
     * @return
     */
    LibraryUser findUserByEmail(String email);

    /**
     * 保存图书馆用户
     * @param user 用户
     */
    void saveLibraryUser(LibraryUser user);
}
