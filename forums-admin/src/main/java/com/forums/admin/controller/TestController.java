package com.forums.admin.controller;

import com.forums.admin.config.MinioProperties;
import com.forums.admin.service.StudyNeiRongService;
import com.forums.admin.service.UserService;
import com.forums.admin.util.MinioUtil;
import com.forums.model.pojo.StudyNeiRong;
import com.forums.model.pojo.User;
import com.forums.model.result.Result;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:10
 */
@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudyNeiRongService studyNeiRongService;
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;
    /**
     *
     *
     *
     *
     * 测试接口
     * 临时接口专用
     *
     *
     *
     *
     *
     * @return
     */
//    @GetMapping("test")
//    public Result<User> a(){
//        System.out.println(userService.selectUsername(10));
//        User user = userService.selectUsername(10);
//        return Result.ok(user);
//    }

//    @GetMapping("/a")
//    public String ba(){
//        userService.getById(17);
//        return "s";
//    }
    // 向学习资源页面添加内容
    @PostMapping("/insertStudy")
    public Result insertNeiRong(@Param("sneirong") String sneirong){
        StudyNeiRong studyNeiRong = new StudyNeiRong();
        studyNeiRong.setSneirong(sneirong);
        boolean save = studyNeiRongService.save(studyNeiRong);
        return save ? Result.ok().message("插入成功") : Result.fail().message("插入失败");
    }

    @SneakyThrows
    @PostMapping("upload")
    public Result upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        MinioUtil.createBucket(minioProperties.getBucket());
        MinioUtil.uploadFile(minioProperties.getBucket(),file,fileName);
        String url = MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(),fileName);
        HashMap<String, String> map = new HashMap<>();
        map.put("url",url);
        return Result.ok(map);
    }
}
