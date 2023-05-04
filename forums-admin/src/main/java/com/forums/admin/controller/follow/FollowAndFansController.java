package com.forums.admin.controller.follow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.FansService;
import com.forums.admin.service.FollowService;
import com.forums.model.pojo.Fans;
import com.forums.model.pojo.Follow;
import com.forums.model.result.Result;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:39
 */

@RestController
@CrossOrigin
public class FollowAndFansController {

    @Resource
    private FollowService followService;
    @Resource
    private FansService fansService;


    /**
     * 关注操作
     * @param userId
     * @param folleredUser
     * @return
     */
    @PostMapping("insertFollow/{userId}/{folleredUser}")
    public Result insertFollow(@PathVariable String userId,
                               @PathVariable String folleredUser){
        // 关注操作
        boolean follow = followService.follow(userId, folleredUser);
        if (follow){
            return Result.ok().message("关注成功~");
        }
        return Result.fail().message("关注失败");
    }

    /**
     * 关注列表
     * @param current 当前页数
     * @param limit  每页显示条数
     * @param userId 当前用户id
     * @return
     */
    @GetMapping("followList/{current}/{limit}/{userId}")
    public Result followList(@PathVariable Integer current,
                             @PathVariable Integer limit,
                             @PathVariable String userId){
        Page<Follow> page = new Page<>(current,limit);
        Page<Follow> flList = followService.followList(page, userId);
        if (flList.getRecords().size() != 0){
            return Result.ok(flList);
        }return Result.fail().message("您还没有关注其他人哦~");
    }

    /**
     * 粉丝列表
     * @param current  当前页数
     * @param limit   每页显示条数
     * @param userId   当前用户id
     * @return
     */
    @GetMapping("fansList/{current}/{limit}/{userId}")
    public Result fansList(@PathVariable Integer current,
                           @PathVariable Integer limit,
                           @PathVariable String userId){
        Page<Fans> page = new Page<>(current,limit);
        Page<Fans> fansPage = fansService.fansList(page, userId);
        if (fansPage.getRecords().size() != 0){
            return Result.ok(fansPage);
        }return Result.fail().message("您还没有粉丝哦~");
    }

    /**
     * 判断用户是关注
     * @param userId
     * @param followId
     * @return
     */
    @GetMapping("pdFollow/{userId}/{followId}")
    public Result pdFollow(@PathVariable String userId,
                           @PathVariable String followId){
        HashMap<String, Boolean> map = new HashMap<>();
        boolean b = followService.pdFollowFlog(userId, followId);
        map.put("followFlag",b);
        return Result.ok(map);
    }


    /**
     * 取消关注操作
     * @param userId  当前登录用户id
     * @param followedId  取消关注用户id
     * @return
     */
    @PostMapping("cancel/{userId}/{followedId}")
    public Result cancelFollow(@PathVariable String userId,
                              @PathVariable String followedId){
        boolean a = followService.cancelFollow(userId, followedId);
        boolean b = fansService.cancelFans(userId,followedId);
        if (a){
            return Result.ok().message("取消关注成功");
        }
        return Result.fail().message("取消关注异常");
    }

}
