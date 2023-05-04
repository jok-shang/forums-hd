package com.forums.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.mapper.DianZanMapper;
import com.forums.admin.mapper.FansMapper;
import com.forums.admin.mapper.UserMapper;
import com.forums.admin.mapper.WenZhangMapper;
import com.forums.model.pojo.DianZan;
import com.forums.model.pojo.Fans;
import com.forums.model.pojo.WenZhang;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/5/2 12:04
 */

public class MapperTest extends BaseController{
    @Resource
    private DianZanMapper dianZanMapper;
    @Resource
    private FansMapper fansMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WenZhangMapper wenZhangMapper;
    @Test
    public void a(){
        Page<WenZhang> page = new Page<>(1,2);
        Page<WenZhang> all = wenZhangMapper.getAll(page);
        System.out.println(all);
    }

    // 点赞查询Mapper测试
//    @Test
//    public void dianzanListTest(){
//        Page<DianZan> page = new Page<>(1,2);
//        Page<DianZan> dianZanPage = dianZanMapper.selectDianZanList(page, 1653369544745431042);
//        Assert.assertSame("ok",dianZanPage,dianZanMapper.selectDianZanList(page,1653369544745431042));
//    }
//
//    // 粉丝集合查询
//    @Test
//    public void fansListTest(){
//        Page<Fans> page = new Page<>(1,2);
//        Page<Fans> fansPage = fansMapper.fansList(page, 18);
//        Assert.assertSame("ok",fansPage,fansMapper.fansList(page,18));
//    }
//
//    // 粉丝数量
//    @Test
//    public void fansCount(){
//        Integer integer = fansMapper.fansCount(18);
//        Assert.assertSame("ok",1,fansMapper.fansCount(10));
//    }
//
//    // 文章分类集合查询
//    @Test
//    public void wenzhangTest(){
//        Page<WenZhang> page = new Page<>(1,2);
//        Page<WenZhang> allBySid = wenZhangMapper.getAllBySid(page, 1);
//        Assert.assertSame("ok",allBySid,wenZhangMapper.getAllBySid(page,1));
//    }


}
