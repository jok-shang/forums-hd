package com.forums.admin.util;

import org.springframework.util.DigestUtils;

/**
 * @auther 尚智江
 * @Date 2023/4/17 21:07
 */

/**
 * MD5 算法用户密码加密
 */
public class MD5Utils {
    public String getMD5Password(String password , String salt){
        // md5 加密算法的调用（进行三次加密）
        for (int i = 0 ; i < 3; i++){
            password = DigestUtils.md5DigestAsHex((salt + password +salt).getBytes()).toUpperCase();
        }
        // 返回加密之后的密码
        return password;
    }
}
