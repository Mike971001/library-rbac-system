package com.demo.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 16:57:29
 * @description: 注册用户
 */
@Data
public class RegisterUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;
}
