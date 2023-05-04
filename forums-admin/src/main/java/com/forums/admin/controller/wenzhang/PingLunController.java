package com.forums.admin.controller.wenzhang;

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
     * @param pingLun  pinglun实体类
     * @return  result
     */
    @PostMapping("insertPL")
    public Result insertPingLun(@RequestBody PingLun pingLun){
        System.out.println(pingLun);
        pingLun.setCreateTime(new Date());
//        System.out.println(new Date());
        boolean save = pingLunService.save(pingLun);
        return save ? Result.ok().message("发表评论成功") : Result.fail().message("发表评论失败");
    }


    /**
     * 获取文章下的评论列表
     * @param tid 文章id
     * @return result
     */
    @GetMapping("/getPL/{tid}")
    public Result getPLList(@PathVariable Integer tid){
        List<PingLun> list = pingLunService.plList(tid);
        if (list == null){
            return Result.ok().message("暂时没有评论哦~");
        }
        return Result.ok(list);
    }

    /**
     * 根据评论 pid 删除评论
     * @param pid  评论id
     * @return
     */
    @PostMapping("deletePl/{pid}")
    public Result deletePL(@PathVariable Integer pid){
        boolean b = pingLunService.deletePL(pid);
        if (b){
            return Result.ok().message("删除评论成功");
        }
        return Result.fail().message("删除评论异常");
    }

}
