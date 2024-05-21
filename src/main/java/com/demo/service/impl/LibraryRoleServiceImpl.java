package com.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.demo.entity.LibraryRole;
import com.demo.entity.dto.ApiResponse;
import com.demo.entity.enums.ApiResponseEnums;
import com.demo.mapper.LibraryRoleMapper;
import com.demo.service.LibraryRoleService;
import com.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 15:49:56
 * @description: 图书馆 角色 业务实现层
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class LibraryRoleServiceImpl implements LibraryRoleService {

    @Autowired
    private LibraryRoleMapper roleMapper;

    @Override
    public ApiResponse addLibraryRole(LibraryRole libraryRole) {
        String checkMsg = "";

        if (StrUtil.isBlank(libraryRole.getRoleName())){
            checkMsg = checkMsg + "角色名称不能为空";
        }

        if (StrUtil.isBlank(libraryRole.getRoleCode())){
            checkMsg = checkMsg + "角色代码不能为空";
        }

        // 没有通过校验
        if (!StrUtil.isBlank(checkMsg)){
            return ResponseUtil.error(ApiResponseEnums.MISSING_PARAMETER);
        }

        // 校验是否有重名的角色
        int checkSum = 0 ;

        checkSum = roleMapper.checkLibraryRoleSameRoleName(libraryRole);

        if (checkSum != 0){
            return ResponseUtil.error(ApiResponseEnums.ROLE_NAME_SAME);
        }

        checkSum = roleMapper.checkLibraryRoleSameRoleCode(libraryRole);
        if (checkSum != 0){
            return ResponseUtil.error(ApiResponseEnums.ROLE_CODE_SAME);
        }

        roleMapper.saveLibraryRole(libraryRole);

        return ResponseUtil.success("添加角色成功,新生成角色id: "+ libraryRole.getRoleId());
    }


    @Override
    public ApiResponse batchDeleteLibraryRole(List<Integer> roleIds) {

        if (CollUtil.isEmpty(roleIds)){
            return ResponseUtil.error(ApiResponseEnums.PARAMETER_EMPTY);
        }

        if (roleIds.contains(1)) {
            return ResponseUtil.error(ApiResponseEnums.REMOVE_ROLE_ADMIN);
        }
        // list去重
        List<Integer> distinctList = roleIds.stream().distinct().collect(Collectors.toList());
        int deletedSum = roleMapper.checkLibraryRoleIsDeleted(distinctList);

        if (deletedSum > 0){
            return ResponseUtil.error(ApiResponseEnums.ROLE_STATUS_DELETED);
        }
        roleMapper.batchLogicDeleteLibraryRole(roleIds);
        return ResponseUtil.success("操作成功");
    }


    @Override
    public ApiResponse batchDisableLibraryRole(List<Integer> roleIds) {
        // 去重
        if (CollUtil.isEmpty(roleIds)){
            return ResponseUtil.error(ApiResponseEnums.PARAMETER_EMPTY);
        }
        // 校验是否包含管理员
        if (roleIds.contains(1)){
            return ResponseUtil.error(ApiResponseEnums.REMOVE_ROLE_ADMIN);
        }
        // list去重
        List<Integer> distinctList = roleIds.stream().distinct().collect(Collectors.toList());
        int deletedSum = roleMapper.checkLibraryRoleIsDeleted(distinctList);

        if (deletedSum > 0){
            return ResponseUtil.error(ApiResponseEnums.ROLE_STATUS_DELETED);
        }

        int disabledSum = roleMapper.checkLibraryRoleIsDisable(distinctList);

        if (disabledSum > 0){
            return ResponseUtil.error(ApiResponseEnums.ROLE_STATUS_CHANGED);
        }
        roleMapper.batchDisableLibraryRole(distinctList);

        return ResponseUtil.success("操作成功");
    }

    @Override
    public ApiResponse updateLibraryRole(LibraryRole libraryRole) {
        String msg = "";

        if (libraryRole.getRoleId()== null){
            msg = msg +"角色唯一标识不能为空";
            return ResponseUtil.error(500,msg);
        }

        assert libraryRole.getRoleId() != null;

        if (libraryRole.getRoleId().equals(1)){
           msg = msg + "管理员角色禁止操作";
        }
        if (StrUtil.isEmpty(libraryRole.getRoleName())){
            msg = msg + "角色名称不能为空";
        }

        if (StrUtil.isEmpty(libraryRole.getRoleCode())){
            msg  = msg + "角色编码不能为空";
        }

        if (StrUtil.isNotBlank(msg)){
            return ResponseUtil.error(ApiResponseEnums.ILLEGAL_OPERATION.getResponseCode(),msg);
        }

        int sameRoleName = roleMapper.checkLibraryRoleSameRoleName(libraryRole);

        if (sameRoleName > 1){
            return  ResponseUtil.error(ApiResponseEnums.ROLE_NAME_SAME);
        }

        int sameRoleCode = roleMapper.checkLibraryRoleSameRoleCode(libraryRole);
        if (sameRoleCode >1){
            return ResponseUtil.error(ApiResponseEnums.ROLE_CODE_SAME);
        }
        roleMapper.updateLibraryRoleByRoleId(libraryRole);
        return ResponseUtil.success("操作成功");
    }

    @Override
    public ApiResponse getLibraryRoleList() {
        List<LibraryRole> roleList = roleMapper.getLibraryRoleList();
        return ResponseUtil.success(roleList);
    }
}
