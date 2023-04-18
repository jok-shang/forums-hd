package com.forums.admin.controller.wenzhang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.WenZhang;
import com.forums.model.result.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @auther 尚智江
 * @Date 2023/4/18 19:16
 */
@RestController
@CrossOrigin
public class WenZhangController {

    @Resource
    private WenZhangService wenZhangService;




    /**
     * 发布文章
     * @param wz 文章
     * @return result
     */
    @PostMapping("/insert")
    public Result insertWenZhang(@RequestBody WenZhang wz){
        wz.setCreateTime(new Date());
        wz.setIsDelete(0);
        boolean save = wenZhangService.save(wz);
        if (save)
            return Result.ok().message("发布成功");
        return Result.fail().message("发布失败");
    }

    /**
     * 模糊根据标题模糊搜索（分页）
     * @param current  当前页
     * @param limit    每页显示条数
     * @param wenZhang  文章标题参数
     * @return Result
     */
    @PostMapping("findAllwzPage/{current}/{limit}")
    public Result findAllPageWenZhang(@PathVariable Integer current,
                                      @PathVariable Integer limit,
                                      @RequestBody(required = false) WenZhang wenZhang){
        // 创建page对象 ，传递当前页，每页记录
        Page<WenZhang> page = new Page<>(current,limit);
        // 构建条件
        QueryWrapper<WenZhang> queryWrapper = new QueryWrapper<>();
        String biaoti = wenZhang.getBiaoTi();// 按标题查询
        if (!StringUtils.isEmpty(biaoti)){
            queryWrapper.like("biaoti",wenZhang.getBiaoTi());
        }
        // 调用方法实现分页
        Page<WenZhang> wz = wenZhangService.page(page,queryWrapper);
        return Result.ok(wz);
    }
}
