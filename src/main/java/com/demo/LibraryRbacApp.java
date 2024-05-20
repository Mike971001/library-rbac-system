package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 13:51:10
 * @description: 图书馆Rbac启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.demo.mapper"})
public class LibraryRbacApp {
    public static void main(String[] args) {
        SpringApplication.run(LibraryRbacApp.class,args);
    }
}
