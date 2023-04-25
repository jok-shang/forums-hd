package com.forums.admin.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.DianZanService;
import com.forums.admin.service.UserService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.DianZan;
import com.forums.model.pojo.User;
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
    @Resource
    private UserService userService;

    /**
     * 返回当前登录用户的点赞列表
     * @param uid  当前登录用户的uid
     * @return
     */
    @GetMapping("dianZanList/{current}/{limit}/{uid}")
    public Result dianZanList(@PathVariable Integer current,
                              @PathVariable Integer limit,
                              @PathVariable Integer uid){
        Page<DianZan> page = new Page<>(current,limit);
        Page<DianZan> list = dianZanService.selectDianZanList(page,uid);

        return Result.ok(list);
    }



    /**
     * 个人中心查询自己发的文章
     * @param current
     * @param limit
     * @param uid
     * @return
     */
    @GetMapping("findAllwzByUserId/{current}/{limit}/{uid}")
    public Result findAll(@PathVariable Integer current,
                          @PathVariable Integer limit,
                          @PathVariable Integer uid){
        Page<WenZhang> page = new Page<>(current,limit);
        QueryWrapper<WenZhang> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
//        List<WenZhang> list = wenZhangService.list(queryWrapper);
        Page<WenZhang> list = wenZhangService.page(page, queryWrapper);
        return Result.ok(list);
    }

    @GetMapping("getUserByUid/{uid}")
    public Result getUserByUid(@PathVariable Integer uid){
        User userByUid = userService.getUserByUid(uid);
        return Result.ok(userByUid);
    }
}
