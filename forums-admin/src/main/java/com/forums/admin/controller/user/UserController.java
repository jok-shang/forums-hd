package com.forums.admin.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.forums.admin.service.UserService;
import com.forums.admin.util.JwtUtils;
import com.forums.admin.util.MD5Utils;
import com.forums.model.pojo.User;
import com.forums.model.result.Result;
import com.forums.model.result.ResultCodeEnum;
import com.forums.model.vo.UserTokenVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:39
 */
@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 注册用户  发送验证码
     * @param user 用户信息（用户名和邮箱）
     * @return Result
     * 获取前端传的 用户名和邮箱，向邮箱中发送验证码
     */
    @PostMapping("/insertUser1")
    public Result insertUser(@RequestBody User user){
        // 查询用户名和邮箱是否存在
        int count1 = userService.count(new QueryWrapper<User>()
                        .eq("username",user.getUsername()));
        int count2 = userService.count(new QueryWrapper<User>().eq("email",user.getEmail()));

        if (count1 > 0){
            return Result.fail().message("用户名已经存在");
        }
        if (count2 > 0){
            return Result.fail().message("邮箱已经存在");
        }
        if (userService.zcFsYzm(user)){
            return Result.ok().message("验证码发送成功");
        }
        return Result.fail().message("验证码发送异常");
    }


    /**
     * 将表单中的用户数据添加到表单
     * @param user 用户VO信息（多验证码字段在数据库不存在在实体类中用注解表示和redis中发送给用户邮箱的验证码做匹配）
     * @return Result
     */
    @PostMapping("insertUser2")
    public Result insertUser2(@RequestBody User user){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String yzm = user.getYzm();
        if (yzm.equals(operations.get(user.getEmail()))){
            if (userService.Md5(user)){
                return Result.ok().message("用户注成功");
            }
            return Result.fail().message("用户注册异常");
        }
        return Result.fail().message("验证码错误");
    }


    /**
     * 登录向前端发送token
     * @param user user
     * @return result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User one = userService.selectUserByEmail(user.getEmail());
//        System.out.println(one);
        try {
//            System.out.println(one);
            // TODO 用户输入密码加密步骤
            String oldPassword = one.getPassword();
            String salt = one.getSalt();
            MD5Utils md5Util = new MD5Utils();
            String md5Password = md5Util.getMD5Password(user.getPassword(), salt);
            // TODO 判断用户输入密码和数据库中的密码加密后是否一致
            if (oldPassword.equals(md5Password)) {
                // TODO 生成token
                JwtUtils jwtUtil = new JwtUtils();
                String token = jwtUtil.creatToken(one);
                //TODO 向UserTokenVo中添加token信息
                one.setToken(token);
                UserTokenVO userTokenVO = new UserTokenVO(one.getUid(),one.getUsername(),
                        one.getEmail(),one.getHeadImage(),one.getToken());
                // TODO 规定向前端返回的json
                return Result.ok(userTokenVO).message("登录成功");
            }
            return Result.fail().message("登录账户/密码错误");
        } catch (Exception e) {
            Result.fail().message("账号异常");
        }
        return Result.fail().message("账号异常");
    }

    /**
     * 前端使用路由守卫判断token是否过期
     * 检查token是否过期
     * @param request 头请求
     * @return result
     */
    @GetMapping("/checkToken")
    public Result checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        JwtUtils jwtUtil = new JwtUtils();
        boolean pdToken = jwtUtil.checkToken(token);
        if (pdToken){
            return Result.ok();
        }
        return Result.build(pdToken, ResultCodeEnum.LOGIN_AURH);
    }


}
