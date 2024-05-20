package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:22:14
 * @description: 用户角色 实体类
 */
@Data
public class LibraryUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer userRoleId;

    /**
     * 用户唯一标识
     */
    private Integer userId;

    /**
     * 角色唯一标识
     */
    private Integer roleId;
}
