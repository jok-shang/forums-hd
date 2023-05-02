package com.forums.admin;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/5/2 12:35
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class BaseController {
    @Resource
    public MockMvc mockMvc;


    @Before
    public void init() {
        System.out.println("----------开始测试------------");
    }

    @After
    public void after() {
        System.out.println("----------测试结束------------");
    }


}
