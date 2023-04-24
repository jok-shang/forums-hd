package com.forums.admin.controller.dianzan;

import com.forums.admin.service.ShouCangService;
import com.forums.model.pojo.ShouCang;
import com.forums.model.result.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/4/24 19:01
 */

@RestController
@CrossOrigin
public class ShouCangController {

    @Resource
    private ShouCangService shouCangService;

    /**
     * 收藏操作
     * @param shouCang
     * {
     *      "tid": 1,   文章id
     *      "uid": 18,  当前登录用户id
     *      "count": 1   收藏+1 / 取消收藏-1
     * }
     * @return
     */
    @PostMapping("/shoucang")
    public Result shoucang(@RequestBody ShouCang shouCang){
        Integer a = shouCang.getCount();
        boolean b = shouCangService.shoucang(shouCang);
        if (a > 0){
            return Result.ok().message("收藏成功");
        }
        if (a < 0){
            return Result.ok().message("取消收藏成功");
        }
        return Result.fail().message("操作异常");
    }
}
