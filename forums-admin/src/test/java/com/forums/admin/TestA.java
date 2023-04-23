package com.forums.admin;

import com.forums.admin.mapper.PingLunMapper;
import com.forums.admin.service.PingLunService;
import com.forums.model.pojo.PingLun;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 23:16
 */
@SpringBootTest
public class TestA {

    @Resource
    private PingLunService pingLunService;



    @Test
    public void  a(){
        int a = 1;
        if (true && (a>0)){
            System.out.println("a");
        }
        if (true && (a<0)){
            System.out.println("b");
        }
    }

    @Test
    public void b(){
        List<PingLun> pl = pingLunService.getPl(1);
        System.out.println(pl);
    }
}
