package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:13:23
 * @description: 图书馆角色
 */
@Data
public class LibraryRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色唯一标识
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态 0 正常(默认状态) 1 停用 2删除
     */
    private String roleStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
