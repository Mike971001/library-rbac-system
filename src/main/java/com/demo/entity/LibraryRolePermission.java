package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:20:46
 * @description: 图书馆角色权限
 */
@Data
public class LibraryRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer rolePermissionId;

    /**
     * 角色标识
     */
    private Integer roleId;

    /**
     * 权限标识
     */
    private Integer permissionId;
}
