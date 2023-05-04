package com.forums.admin.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.forums.admin.config.MinioProperties;
import com.forums.admin.service.UploadImageService;
import com.forums.admin.service.UserService;
import com.forums.admin.service.WenZhangService;
import com.forums.admin.util.MD5Utils;
import com.forums.admin.util.MinioUtil;
import com.forums.model.pojo.User;
import com.forums.model.result.Result;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

/**
 * @auther 尚智江
 * @Date 2023/4/17 23:18
 */
@RestController
@CrossOrigin
public class UpdateUserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UploadImageService uploadImageService;
    @Resource
    private WenZhangService wenZhangService;
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;

    /**
     * 修改密码1  向用户的邮箱发送验证码
     * @param email  邮箱
     * @return Result
     */
    @GetMapping("/updatePassword1")
    public Result updatePassword1(@Param("email") String email){
        // TODO 判断修改密码的验证码是否发送成功
        boolean b = userService.fsYzmUP(email);
        return b ? Result.ok().message("验证码发送成功") : Result.fail().message("验证码发送异常,请检查邮箱信息");
    }

    /**
     * 修改用户密码提交表单
     * @param user
     * @return
     */
    @PostMapping("/updatePasswoerd2")
    public Result updatePassword2(@RequestBody User user){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // TODO 查询redis中的验证码
        String yzm = user.getYzm();
        // TODO 判断用户输入的验证码和redis中是否一致
        if (yzm.equals(operations.get(user.getEmail()))){
            boolean b = userService.updatePassword(user);
            // TODO 判断用户修改是否成功
            if (b){
                return Result.ok().message("修改成功");
            }
            return Result.fail().message("修改密码异常");
        }
        return Result.fail().message("验证码错误");
    }
    /**
     * 上传图片
     * @param file 文件
     * @return result
     */
    @PostMapping("/image")
    public Result upLoadImage(@RequestParam("file") MultipartFile file){
        String result = "失败";
        HashMap<String,String> map = new HashMap<>();
        if(!file.isEmpty()){
            String path = uploadImageService.uploadQNImg(file);
            if(path.equals(result)){
                return Result.fail();
            }else{
                map.put("imageUrl",path);
                return Result.ok(map);
            }
        }
        return Result.fail();
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        // 将密码解密
        // TODO 重新写service
        String oldPassword = user.getPassword();
        // 随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 调用 md5 算法加密
        MD5Utils md5Utils = new MD5Utils();
        String md5Password = md5Utils.getMD5Password(oldPassword, salt);
        // 插入用户修改后的密码及其盐值
        user.setSalt(salt);
        user.setPassword(md5Password);
        boolean b = userService.updateUserById(user);
        return b ? Result.ok().message("修改成功") : Result.fail().message("修改失败");

    }
    /**
     * 修改用户头像
     */
    @SneakyThrows
    @PostMapping("updateImage")
    public Result updateImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("uid") String uid) {
        String fileName = file.getOriginalFilename();
        MinioUtil.createBucket(minioProperties.getBucket());
        MinioUtil.uploadFile(minioProperties.getBucket(),file,fileName);
        String url = MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(),fileName);
        HashMap<String, String> map = new HashMap<>();
        map.put("url",url);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("uid",uid);
                updateWrapper.set("headimage",url);
                userService.update(updateWrapper);
                return Result.ok().message("头像修改成功");

    }
//    @PostMapping("updateImage")
//    public Result updateImage(@RequestParam("file") MultipartFile file,
//                              @RequestParam("uid") String uid){
//        String result = "失败";
//        if(!file.isEmpty()){
//            String path = uploadImageService.uploadQNImg(file);
//            if(path.equals(result)){
//                return Result.fail();
//            }else{
//                UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//                updateWrapper.eq("uid",uid);
//                updateWrapper.set("headimage",path);
//                userService.update(updateWrapper);
//                return Result.ok().message("头像修改成功");
//            }
//        }
//        return Result.fail().message("头像修改失败");
//    }


    /**
     * 文章作者删除文章，删除点赞表和收藏表
     * @param tid
     * @return
     */
    @PostMapping("delete/{tid}")
    public Result deleteWz(@PathVariable Integer tid){
        boolean b = wenZhangService.deleteWz(tid);
        return b ? Result.ok().message("删除文章成功!") : Result.fail().message("删除文章异常");
    }
}
