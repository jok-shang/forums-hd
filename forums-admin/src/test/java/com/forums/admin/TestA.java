package com.forums.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.mapper.DianZanMapper;
import com.forums.admin.mapper.PingLunMapper;
import com.forums.admin.service.PingLunService;
import com.forums.model.pojo.DianZan;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 23:16
 */
@SpringBootTest
public class TestA {

    @Resource
    private PingLunService pingLunService;
    @Resource
    private DianZanMapper dianZanMapper;
    @Resource
    private PingLunMapper pingLunMapper;



//    @Test
//    public void  a(){
//        int a = 1;
//        if (a == 1){
//            System.out.println("a");
//        }
//        if (true && (a<0)){
//            System.out.println("b");
//        }
//    }
//
//    @Test
//    public void b(){
//        Page<DianZan> page = new Page<>(1,2);
//        Page<DianZan> dianZanPage = dianZanMapper.selectDianZanList(page, 18);
//        System.out.println(dianZanPage);
//    }



}
