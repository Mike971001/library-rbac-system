package com.demo.web;

import com.demo.entity.LibraryRole;
import com.demo.entity.dto.ApiResponse;
import com.demo.service.LibraryRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 15:28:48
 * @description:
 */
@RestController
@RequestMapping(value = "/role")
public class LibraryRoleController {

    @Autowired
    private LibraryRoleService roleService;

    /**
     * 查询全部角色列表 TODO 待优化
     * @return
     */
    @GetMapping("/list")
    public ApiResponse getLibraryRoleList(){
        return roleService.getLibraryRoleList();
    }

    /**
     * 添加角色
     * @param libraryRole 角色
     * @return
     */
    @PostMapping("/addRole")
    public ApiResponse addLibraryRole(@RequestBody LibraryRole libraryRole){
        return roleService.addLibraryRole(libraryRole);
    }

    /**
     * 更新角色操作
     * @param libraryRole 角色
     * @return
     */
    @PostMapping("/update")
    public ApiResponse updateLibraryRole(@RequestBody LibraryRole libraryRole){
        return roleService.updateLibraryRole(libraryRole);
    }

    /**
     * 批量删除角色
     * @param roleIds 角色
     * @return
     */
    @DeleteMapping("/delete")
    public ApiResponse batchDeleteLibraryRole(@RequestBody List<Integer> roleIds){
        return roleService.batchDeleteLibraryRole(roleIds);
    }

    /**
     * 批量停用用户
     * @param roleIds 用户
     * @return
     */
    @PutMapping("/disable")
    public ApiResponse batchDisableLibraryRole(@RequestBody List<Integer> roleIds){
        return roleService.batchDisableLibraryRole(roleIds);
    }
}
