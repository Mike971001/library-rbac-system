package com.demo.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 10:38:27
 * @description: 生成随机盐的工具类
 */
public class SaltGenerateUtils {

    private static final int DEFAULT_SALT_LENGTH = 16;

    /**
     * 生成定长随机盐
     * @param length 盐的长度
     * @return
     */
    public static String generateSalt(int length){
        // 定义一个随机数生成器
        SecureRandom random = new SecureRandom();
        // 定义一个字节数组用于保存随机生成的盐值
        byte[] salt = new byte[length];
        // 生成随机盐值
        random.nextBytes(salt);
        // 将随机盐进行Base64编码返回
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 生成16位长度的随机盐
     * @return
     */
    public static String generateSalt(){
        return generateSalt(16);
    }

}
