package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:18
 */
@Service
public interface UserService extends IService<User> {
    User selectUsername(Long uid);

    // 随机生成六位随机数字
    String getRandomCount();

    // 注册发送验证码
    boolean zcFsYzm(User user);

    // md5加密用户密码
    boolean Md5(User user);

    // 根据邮箱查询用户信息
    User selectUserByEmail(String email);

    // 修改密码发送验证码
    boolean fsYzmUP(String email);

    // 修改用户密码
    boolean updatePassword(User user);

    // 修改用户信息
    boolean updateUserById(User user);

    // 根据用户id查询用户信息，用于展示
    User getUserByUid(String uid);

}
