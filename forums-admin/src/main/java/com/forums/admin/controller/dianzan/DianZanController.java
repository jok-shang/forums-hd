package com.forums.admin.controller.dianzan;

import com.forums.admin.service.DianZanService;
import com.forums.model.pojo.DianZan;
import com.forums.model.result.Result;
import com.forums.model.vo.DianZanVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:58
 */
@RestController
@CrossOrigin
public class DianZanController {

    @Resource
    private DianZanService dianZanService;

    /**
     * 用户点赞操作
     * @param dianZan
     * {
     *     "tid": 1,   文章id
     *     "uid": 18,  当前登录用户id
     *     "count": 1   点赞+1 / 取消点赞-1
     * }
     * @return
     */
    @PostMapping("/dianzan")
    public Result dianZan(@RequestBody DianZan dianZan){
        Integer a = dianZan.getCount();
        boolean b = dianZanService.dianZan(dianZan);
        if ((a > 0)){
//            System.out.println(b +","+ (dianZan.getCount() > 0)+"======");
            return Result.ok().message("点赞成功~");
        }
        if ((a < 0)){
            return Result.ok().message("取消点赞成功");
        }
        return Result.fail().message("操作异常");
    }
}
