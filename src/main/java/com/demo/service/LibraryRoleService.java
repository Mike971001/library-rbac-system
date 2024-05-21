package com.demo.service;

import com.demo.entity.LibraryRole;
import com.demo.entity.dto.ApiResponse;

import java.util.List;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 15:48:57
 * @description: 图书馆 角色 服务层
 */
public interface LibraryRoleService {

    /**
     * 添加图书馆用角色
     * @param libraryRole 图书馆角色
     * @return
     */
    ApiResponse addLibraryRole(LibraryRole libraryRole);

    /**
     * 批量删除用户角色
     * @param roleIds 角色列表
     * @return
     */
    ApiResponse batchDeleteLibraryRole(List<Integer> roleIds);

    /**
     * 批量禁用角色
     * @param roleIds 角色列表
     * @return
     */
    ApiResponse batchDisableLibraryRole(List<Integer> roleIds);

    /**
     * 更新角色
     * @param libraryRole 图书馆角色
     * @return
     */
    ApiResponse updateLibraryRole(LibraryRole libraryRole);

    /**
     * 查询列表
     * @return
     */
    ApiResponse getLibraryRoleList();
}
