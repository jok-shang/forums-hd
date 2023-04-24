package com.forums.admin.controller.wenzhang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.*;
import com.forums.model.pojo.PingLun;
import com.forums.model.pojo.User;
import com.forums.model.pojo.WenZhang;
import com.forums.model.result.Result;
import com.forums.model.result.WangEditorVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * @auther 尚智江
 * @Date 2023/4/18 19:16
 */
@RestController
@CrossOrigin
public class WenZhangController {

    @Resource
    private WenZhangService wenZhangService;
    @Resource
    private UploadImageService uploadImageService;
    @Resource
    private UserService userService;
    @Resource
    private PingLunService pingLunService;
    @Resource
    private DianZanService dianZanService;


    /**
     * 文章内上传图片接口
     * @param file  图片
     * @return result
     */
    @PostMapping("/wzuploadImage")
    public WangEditorVO WzUploadImage(@RequestParam(value = "file",required = false) MultipartFile file){
//        List<Map> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        if (!file.isEmpty()){
            String path = uploadImageService.uploadQNImg(file);
            if (path.equals("失败")){
                return WangEditorVO.error(3,"上传失败");
            }else {
                map.put("url",path);
//                list.add(map);
                return WangEditorVO.success(map);
            }
        }
        return WangEditorVO.error(3,"上传失败");
    }

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


    /**
     * 返回文章详情信息
     * @param tid   文章id
     * @param uid   当前登录用户id
     * @return
     */
    @GetMapping("getWzByTid")
    public Result getWzByTid(@Param("tid")Integer tid ,@Param("uid") Integer uid){
        // 根据文章id查询文章
        WenZhang byId = wenZhangService.getById(tid);
        // 判断当前用户是否点赞
        boolean toStart = dianZanService.isToStart(uid, tid);
        byId.setStartFlag(toStart);
        // 根据用户id查询文章作者信息
        User userByUid = userService.getUserByUid(byId.getUid());
        // 根据tid查询评论列表
        List<PingLun> list = pingLunService.plList(tid);

        // 拼接返回参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("wenzhang",byId);
        map.put("user",userByUid);
        map.put("pinglun",list);
        return Result.ok(map);
    }
}
