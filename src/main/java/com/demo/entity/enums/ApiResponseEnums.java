package com.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 15:00:22
 * @description: 接口响应枚举类
 */
@AllArgsConstructor
@Getter
public enum ApiResponseEnums {

    SYSTEM_ERROR(500,"系统异常"),

    ILLEGAL_OPERATION(4567,"非法操作"),

    MISSING_PARAMETER(4000,"缺少参数,请检查参数"),
    ROLE_NAME_BLANK(4001,"角色名称不能为空"),
    ROLE_CODE_BLANK(4002,"角色编码不能为空"),
    ROLE_NAME_SAME(4003,"角色名称重复"),
    ROLE_CODE_SAME(4004,"角色代码重复"),
    REMOVE_ROLE_ADMIN(4005,"当前操作包含管理员角色,请重新选择"),
    PARAMETER_EMPTY(4006,"参数不能为空,请检查参数"),
    ROLE_STATUS_DELETED(4007,"角色已经被删除"),
    ROLE_STATUS_CHANGED(4008,"角色状态发生改变,请刷新后重试"),



    GENERAL_EXCEPTION(5000,"通用异常"),
    USER_NAME_SAME(5001,"用户名重复"),
    USER_EMAIL_SAME(5002,"用户邮箱重复"),
    USER_PHONE_SAME(5003,"用户手机号重复"),
    ;

    private final Integer responseCode;
    private final String responseMessage;
}
