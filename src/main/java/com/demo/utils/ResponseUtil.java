package com.demo.utils;

import com.demo.entity.dto.ApiResponse;
import com.demo.entity.enums.ApiResponseEnums;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 14:56:05
 * @description: 封装响应结果
 */
public class ResponseUtil {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(200, message, null);
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> error(ApiResponseEnums apiResponseEnums){
        return new ApiResponse<>(apiResponseEnums.getResponseCode(), apiResponseEnums.getResponseMessage());
    }
}
