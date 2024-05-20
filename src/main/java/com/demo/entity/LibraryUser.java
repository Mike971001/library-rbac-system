package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:20:10
 * @description: 图书馆用户实体类
 */
@Data
public class LibraryUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别 默认无可奉告
     */
    private String gender;

    /**
     * 用户自述
     */
    private String userDesc;

    /**
     * 用户状态 0 正常(默认) 1 停用 2 删除
     */
    private String userStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
