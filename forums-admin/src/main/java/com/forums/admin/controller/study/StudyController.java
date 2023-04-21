package com.forums.admin.controller.study;

import com.forums.admin.service.StudyNeiRongService;
import com.forums.admin.service.StudyService;
import com.forums.model.pojo.Study;
import com.forums.model.pojo.StudyNeiRong;
import com.forums.model.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/20 19:39
 */
@RestController
@CrossOrigin
public class StudyController {

    @Resource
    private StudyService studyService;
    @Resource
    private StudyNeiRongService studyNeiRongService;

    @GetMapping("/getAll")
    public Result getAll(){
        List<Study> list = studyService.list();
        return Result.ok(list);
    }

    // 根据数据id查询子数据列表
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Integer id){
        List<Study> list = studyService.findChlidData(id);
        return Result.ok(list);
    }

    // 根据value 查询内容
    @GetMapping("/getValue/{sid}")
    public Result findValue(@PathVariable Integer sid){
        StudyNeiRong byId = studyNeiRongService.getById(sid);
        return Result.ok(byId);
    }



}
