package com.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 19:00:52
 * @description: 图书馆 枚举类
 */
@Getter
@AllArgsConstructor
public enum LibraryStatus {

    NORMAL("0", "正常"),
    DISABLE("1", "停用"),
    DELETED("2", "删除");

    /**
     * 状态名字
     */
    private final String statusCode;

    /**
     * 状态描述
     */
    private final String statusDesc;

}
