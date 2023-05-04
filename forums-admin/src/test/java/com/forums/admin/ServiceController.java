package com.forums.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.DianZanService;
import com.forums.admin.service.ShouCangService;
import com.forums.admin.service.UserService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.WenZhang;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @auther 尚智江
 * @Date 2023/5/2 13:30
 */
public class ServiceController extends BaseController{

    @Resource
    private UserService userService;
    @Resource
    private WenZhangService wenZhangService;
    @Resource
    private ShouCangService shouCangService;
    @Resource
    private DianZanService dianZanService;
    // 根据文章分类查询文章
//    @Test
//    public void getUserCountTest(){
//        Page<WenZhang> page = new Page<>(1,2);
//        Page<WenZhang> allBySid = wenZhangService.getAllBySid(page, 1);
//        Assert.assertSame("ok",allBySid,wenZhangService.getAllBySid(page,1));
//    }
//
//    // 判断当前用户是否收藏当前文章
//    @Test
//    public void pdShouCang(){
//        Assert.assertSame("ok",false,shouCangService.isToShou(1,18));
//    }
//
//    // 判断当前用户是否点赞当前文章
//    @Test
//    public void pdStart(){
//        Assert.assertSame("ok",true,dianZanService.isToStart(1,18));
//    }

}
