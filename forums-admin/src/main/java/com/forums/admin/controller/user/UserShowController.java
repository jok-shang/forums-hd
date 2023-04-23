package com.forums.admin.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forums.admin.service.DianZanService;
import com.forums.admin.service.UserService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.DianZan;
import com.forums.model.pojo.WenZhang;
import com.forums.model.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/22 17:57
 */
@RestController
@CrossOrigin
public class UserShowController {

    /*
    个人文章列表
    点赞列表
    收藏列表
     */

    @Resource
    private DianZanService dianZanService;
    @Resource
    private WenZhangService wenZhangService;

    /**
     * 返回当前登录用户的点赞列表
     * @param uid  当前登录用户的uid
     * @return
     */
    @GetMapping("dianZanList/{uid}")
    public Result dianZanList(@PathVariable Integer uid){
        List<DianZan> list = dianZanService.selectDianZanList(uid);
        return Result.ok(list);
    }

    @GetMapping("findAllwzByUserId/{uid}")
    public Result findAll(@PathVariable Integer uid){
        QueryWrapper<WenZhang> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<WenZhang> list = wenZhangService.list(queryWrapper);
        return Result.ok(list);
    }
}
