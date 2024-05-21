package com.demo.mapper;

import com.demo.entity.LibraryRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 14:24:04
 * @description: 图书馆 角色持久层接口
 */
@Mapper
public interface LibraryRoleMapper {

    /**
     * 保存图书馆角色
     * @param libraryRole
     * @return
     */
    int saveLibraryRole(LibraryRole libraryRole);

    /**
     * 批量校验用户角色是否删除
     * @param roleIds 用户角色
     * @return
     */
    int checkLibraryRoleIsDeleted(List<Integer> roleIds);

    /**
     * 批量恢复用户角色
     * @param roleIds
     * @return
     */
    int batchNormalLibraryRole(List<Integer> roleIds);

    /**
     * 批量逻辑删除图书馆角色
     * @param roleIds 角色Id列表
     * @return
     */
    int batchLogicDeleteLibraryRole(List<Integer> roleIds);

    /**
     * 批量禁用角色id
     * @param roleIds 角色Id列表
     * @return
     */
    int batchDisableLibraryRole(List<Integer> roleIds);

    /**
     * 修改图书馆角色
     * @param libraryRole 图书馆角色
     * @return
     */
    int updateLibraryRoleByRoleId(LibraryRole libraryRole);

    /**
     * 校验角色名称是否相同
     * @param libraryRole 角色对象
     * @return
     */
    int checkLibraryRoleSameRoleName(LibraryRole libraryRole);

    /**
     * 校验角色代码是否相同
     * @param libraryRole 角色对象
     * @return
     */
    int checkLibraryRoleSameRoleCode(LibraryRole libraryRole);

    /**
     * 校验角色是否被禁用
     * @param roleIds 角色
     * @return
     */
    int checkLibraryRoleIsDisable(List<Integer> roleIds);

    /**
     * 查询角色列表
     * @return
     */
    List<LibraryRole> getLibraryRoleList();

}
