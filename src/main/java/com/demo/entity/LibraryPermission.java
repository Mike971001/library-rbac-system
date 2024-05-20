package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:19:23
 * @description: 图书馆 权限实体类
 */
@Data
public class LibraryPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限唯一标识
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
