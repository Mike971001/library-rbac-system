package com.demo.security;

import com.demo.entity.dto.ApiResponse;
import com.demo.entity.enums.ApiResponseEnums;
import com.demo.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 17:34:39
 * @description: 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ApiResponse nullPointerExceptionResponse(NullPointerException exception){
        log.warn("发生了空指针异常 ~~~~~");
        exception.printStackTrace();
        return ResponseUtil.error(ApiResponseEnums.SYSTEM_ERROR.getResponseCode(),"后台服务发生空指针异常");
    }
}
