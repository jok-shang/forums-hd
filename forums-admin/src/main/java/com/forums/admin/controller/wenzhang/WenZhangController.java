package com.forums.admin.controller.wenzhang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.config.MinioProperties;
import com.forums.admin.service.*;
import com.forums.admin.util.MinioUtil;
import com.forums.model.pojo.PingLun;
import com.forums.model.pojo.User;
import com.forums.model.pojo.WenZhang;
import com.forums.model.result.Result;
import com.forums.model.result.WangEditorVO;
import io.minio.MinioClient;
import lombok.SneakyThrows;
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
    @Resource
    private ShouCangService shouCangService;
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;


    /**
     * 获取所有文章列表 按时间排序 带分页
     * @param current  当前页
     * @param limit  每页显示条数
     * @return result
     */
    @GetMapping("getAll/{current}/{limit}")
    public Result getAll(@PathVariable Integer current,
                         @PathVariable Integer limit){
        Page<WenZhang> page = new Page<>(current,limit);
        Page<WenZhang> all = wenZhangService.getAll(page);
        return Result.ok(all);
    }

    /**
     * 按文章分类查询（sid） 按时间排序  带分页
     * @param current  当前页数
     * @param limit  每页显示条数
     * @param sid  分类的sid
     * @return result
     */
    @GetMapping("getAllBySid/{current}/{limit}")
    public Result getAllBySid(@PathVariable Integer current,
                              @PathVariable Integer limit,
                              @Param("sid") Integer sid){
        Page<WenZhang> page = new Page<>(current,limit);
        Page<WenZhang> allBySid = wenZhangService.getAllBySid(page,sid);
        return Result.ok(allBySid);
    }




    /**
     *  接口一   minio
     * 文章内上传图片接口
     * @param file  图片
     * @return result
     */
    @SneakyThrows
    @PostMapping("/wzuploadImage")
    public WangEditorVO upload(@RequestParam(value = "file",required = false)MultipartFile file){
        String fileName = file.getOriginalFilename();
        MinioUtil.createBucket(minioProperties.getBucket());
        MinioUtil.uploadFile(minioProperties.getBucket(),file,fileName);
        String url = MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(),fileName);
        HashMap<String, String> map = new HashMap<>();
        map.put("url",url);
        return WangEditorVO.success(map);
    }
    /**
     * 接口 二 七牛云
     */
//    @PostMapping("/wzuploadImage")
//    public WangEditorVO WzUploadImage(@RequestParam(value = "file",required = false) MultipartFile file){
////        List<Map> list = new ArrayList<>();
//        HashMap<String,Object> map = new HashMap<>();
//        if (!file.isEmpty()){
//            String path = uploadImageService.uploadQNImg(file);
//            if (path.equals("失败")){
//                return WangEditorVO.error(3,"上传失败");
//            }else {
//                map.put("url",path);
////                list.add(map);
//                return WangEditorVO.success(map);
//            }
//        }
//        return WangEditorVO.error(3,"上传失败");
//    }

    /**
     * 发布文章
     * @param wz 文章
     * @return result
     */
    @PostMapping("/insert")
    public Result insertWenZhang(@RequestBody WenZhang wz){
        wz.setCreateTime(new Date());
        wz.setIsDelete(0);
        wz.setTShou(0);
        wz.setTStart(0);
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
    public Result getWzByTid(@Param("tid")Integer tid ,@Param("uid") String uid){
        // 根据文章id查询文章
        WenZhang byId = wenZhangService.getById(tid);
        // 判断当前用户是否点赞
        boolean toStart = dianZanService.isToStart(uid, tid);
        // 判断当前用户是否收藏
        boolean toShou = shouCangService.isToShou(uid,tid);
        byId.setStartFlag(toStart);
        byId.setShouFlag(toShou);
        // 根据用户id查询文章作者信息
        User userByUid = userService.getUserByUid(byId.getUid());
        // 根据tid查询评论列表
        List<PingLun> list = pingLunService.plList(tid);
        //TODO  文章测试时间
        for (int i = 0 ;i<list.size();i++){
            System.out.println(list.get(i).getCreateTime());
        }

        // 拼接返回参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("wenzhang",byId);
        map.put("user",userByUid);
        map.put("pinglun",list);
        return Result.ok(map);
    }
}
