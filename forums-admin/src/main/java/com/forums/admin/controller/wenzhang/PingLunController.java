package com.forums.admin.controller.wenzhang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forums.admin.service.PingLunService;
import com.forums.model.pojo.PingLun;
import com.forums.model.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/23 14:46
 */

@RestController
@CrossOrigin
public class PingLunController {

    @Resource
    private PingLunService pingLunService;

    /**
     * 发布评论接口
     * @param pingLun
     * @return
     */
    @PostMapping("insertPL")
    public Result insertPingLun(@RequestBody PingLun pingLun){
        System.out.println(pingLun);
        pingLun.setCreateTime(new Date());
        boolean save = pingLunService.save(pingLun);
        return save ? Result.ok().message("发表评论成功") : Result.fail().message("发表评论失败");
    }

    @GetMapping("getPl/{tid}")
    public Result getPl(@PathVariable Integer tid){
        List<PingLun> list = pingLunService.plList(tid);
        return Result.ok(list);
    }
}
