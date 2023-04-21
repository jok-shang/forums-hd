package com.forums.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther 尚智江
 * @Date 2023/4/21 23:16
 */
@SpringBootTest
public class TestA {
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
}
