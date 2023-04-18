package com.forums.admin.controller;

import com.forums.admin.service.StudyNeiRongService;
import com.forums.admin.service.UserService;
import com.forums.model.pojo.StudyNeiRong;
import com.forums.model.pojo.User;
import com.forums.model.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("test")
    public Result<User> a(){
        System.out.println(userService.selectUsername(10));
        User user = userService.selectUsername(10);
        return Result.ok(user);
    }

    @GetMapping("/a")
    public String ba(){
        userService.getById(17);
        return "s";
    }
    // 向学习资源页面添加内容
    @PostMapping("/insertStudy")
    public Result insertNeiRong(@Param("sneirong") String sneirong){
        StudyNeiRong studyNeiRong = new StudyNeiRong();
        studyNeiRong.setSneirong(sneirong);
        boolean save = studyNeiRongService.save(studyNeiRong);
        return save ? Result.ok().message("插入成功") : Result.fail().message("插入失败");
    }


}
