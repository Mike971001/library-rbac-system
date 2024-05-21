package com.demo.mapper;

import com.demo.entity.LibraryUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:24:39
 * @description: 图书馆 用户 持久层
 */
@Mapper
public interface LibraryUserMapper {

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return
     */
    LibraryUser findUserByUsername(@Param("username") String username);

    /**
     * 通过邮箱查询用户
     * @param email 邮箱
     * @return
     */
    LibraryUser findUserByEmail(@Param("email") String email);

    /**
     * 保存用户
     * @param libraryUser 用户对象
     * @return
     */
    int saveLibraryUser(LibraryUser libraryUser);

    /**
     * 校验用户是否重名
     * @param user 用户
     * @return
     */
    int checkLibraryUserSameName(LibraryUser user);

    /**
     * 校验邮箱是否重复
     * @param user 用户
     * @return
     */
    int checkLibraryUserSameEmail(LibraryUser user);
}