package com.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author: 今天不加班
 * @date: 2024/5/21 11:03:40
 * @description:
 */
@Component
public class SaltedPasswordEncoder implements PasswordEncoder {

    /**
     * 盐的长度
     */
    private static final int SALT_LENGTH = 16;

    /**
     * 随机生成随机密码
     */
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * 生成随机盐
     * @return
     */
    private byte[] generateSalt(){
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }

    /**
     * 密码加密
     * @param rawPassword
     * @param salt
     * @return
     */
    public String encode(CharSequence rawPassword, byte[] salt) {
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashPassword(rawPassword, salt);
        return saltBase64 + ":" + hashedPassword;
    }

    /**
     * hash加密
     * @param rawPassword 原始密码
     * @param salt 盐
     * @return
     */
    public String hashPassword(CharSequence rawPassword, byte[] salt) {
        // 使用BCrypt或其他加密算法进行加密
        // 这里只是一个简单示例，请使用安全的密码哈希算法
        return Base64.getEncoder().encodeToString((new String(salt) + rawPassword).getBytes());
    }

    @Override
    public String encode(CharSequence rawPassword) {
        // 生成盐
        byte[] salt = generateSalt();
        return encode(rawPassword,salt);
    }

    /**
     * 密码比较
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split(":");
        if (parts.length != 2) {
            return false;
        }
        String salt = parts[0];
        String storedHash = parts[1];
        String computedHash = hashPassword(rawPassword, Base64.getDecoder().decode(salt));
        return storedHash.equals(computedHash);
    }
}
