package com.forums.admin;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @auther 尚智江
 * @Date 2023/5/2 12:33
 */
public class ControllerTest extends BaseController{

    // 学习资源controller测试
    @Test
    public void StudyGetAll() throws Exception {
        ResultActions perform =
                mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8808/getAll/1/2")
                        .contentType(MediaType.ALL)
                );
        perform.andReturn().getResponse().setCharacterEncoding("UTF-8");
        perform.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    // 文章详情controller测试
    @Test
    public void wenzhangTest() throws Exception {
        ResultActions perform =
                mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8808/getWzByTid")
                        .param("tid","1")
                        .contentType(MediaType.ALL)
                );
        perform.andReturn().getResponse().setCharacterEncoding("UTF-8");
        perform.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    // 文章板块controller测试
    @Test
    public void sectionTest() throws Exception {
        ResultActions perform =
                mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8808//getSectionAndWzCount")
                        .contentType(MediaType.ALL)
                );
        perform.andReturn().getResponse().setCharacterEncoding("UTF-8");
        perform.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }



}
