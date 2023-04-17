package com.forums.admin.controller;

import com.forums.admin.service.UserService;
import com.forums.model.pojo.User;
import com.forums.model.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


}
