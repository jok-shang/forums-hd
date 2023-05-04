package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.UserMapper;
import com.forums.admin.service.UserService;
import com.forums.admin.util.EmailUtils;
import com.forums.admin.util.MD5Utils;
import com.forums.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public User selectUsername(Long uid) {
        return userMapper.selectUsername(uid);
    }

    /**
     * 随机生成六位随机数字
     * @return
     */
    @Override
    public String getRandomCount() {
        Random random = new Random();
        StringBuilder value = new StringBuilder(String.valueOf(random.nextInt(9) + 1));
        for (int i = 0; i < 5; i++){
            value.append(random.nextInt(10));
        }
        return value.toString();
    }

    /**
     * 注册步骤一向用户发送验证码
     * @param user
     * @return
     */
    @Override
    public boolean zcFsYzm(User user) {
        //TODO 生成6位随机整数
        String randomCount = this.getRandomCount();
        //TODO 向用户邮箱发送验证码
        EmailUtils emailUtils = new EmailUtils();
        try {
            emailUtils.toEmail(user.getEmail(), "欢迎'" + user.getUsername() +
                    "'注册Index Forum平台", "您的注册验证码为:   " + randomCount);
        } catch (MessagingException e) {
            return false;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(user.getEmail(),randomCount, 3, TimeUnit.MINUTES);
        return true;
    }

    /**
     * md5加密用户密码
     * @param user
     * @return
     */
    @Override
    public boolean Md5(User user) {
        MD5Utils md5Utils = new MD5Utils();
        // 用户输入的密码
        String oldPassword = user.getPassword();
        // 随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 调用 md5 算法加密
        String md5Password = md5Utils.getMD5Password(oldPassword, salt);
        // 补全用户信息
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setHeadImage("http://image.szj.icu/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20220607211837.jpg");
        user.setIsDelete(0);
        user.setGender(0);
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        return insert > 0;
    }

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    @Override
    public User selectUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 修改密码发送验证码
     * @param email 用户邮箱
     * @return Result
     */
    @Override
    public boolean fsYzmUP(String email) {
        //TODO 生成6位随机整数
        String randomCount = this.getRandomCount();
        //TODO 向用户邮箱发送验证码
        EmailUtils emailUtils = new EmailUtils();
        try {
            emailUtils.toEmail(email, "修改Index Forum平台密码" ,"您本次修改账号密码的验证码为 : "+ randomCount);
        } catch (MessagingException e) {
            return false;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        TimeUnit.MINUTES
        operations.set(email,randomCount, 3, TimeUnit.MINUTES);
        return true;
    }

    /**
     * 修改密码
     * @param user 修改密码
     * @return Result
     */
    @Override
    public boolean updatePassword(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",user.getEmail());
        User one = userMapper.selectOne(queryWrapper);
        System.out.println("查询出来的用户信息"+user);
        if (one != null){
            String salt = UUID.randomUUID().toString().toUpperCase();
            MD5Utils md5Util = new MD5Utils();
            String newPassword = md5Util.getMD5Password(user.getPassword(), salt);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper
                    .eq("email",user.getEmail());
            updateWrapper.set("salt",salt);
            updateWrapper.set("password",newPassword);
            int update = baseMapper.update(user,updateWrapper);
            System.out.println("修改影响行数"+update);
            return update > 0;
        }
        return false;
    }

    @Override
    public boolean updateUserById(User user) {
        int i = userMapper.updateById(user);
        return i > 0;
    }

    /**
     * 根据用户id查询  用于展示用户数据
     * @param uid 用户id
     * @return result
     */
    @Override
    public User getUserByUid(String uid) {
        User userByUid = userMapper.getUserByUid(uid);
        return userByUid;
    }


}
