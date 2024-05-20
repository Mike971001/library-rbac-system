package com.demo;

import com.demo.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: 今天不加班
 * @date: 2024/5/20 13:51:47
 * @description: 图书rbacApp测试启动类类
 */
@SpringBootTest(classes = LibraryRbacApp.class)
public class LibraryRbacAppTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void contextLoads(){
        String adminToken = jwtUtils.generateJwtToken("admin");
        String tokenUsername = jwtUtils.getUsernameFromJwtToken(adminToken);
        boolean flag = jwtUtils.validateJwtToken(adminToken.substring(5));
        System.out.println(tokenUsername);
        System.out.println(adminToken);
        System.out.println(flag);
    }
}
