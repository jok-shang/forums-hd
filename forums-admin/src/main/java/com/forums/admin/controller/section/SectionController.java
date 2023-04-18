package com.forums.admin.controller.section;

import com.forums.admin.service.SectionService;
import com.forums.model.pojo.Section;
import com.forums.model.result.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/18 15:47
 */
@RestController
@CrossOrigin
public class SectionController {

    @Resource
    private SectionService sectionService;


    /**
     * 获取分类列表下的文章集合和文章数
     * @param sid sid
     * @return Result
     */
    @GetMapping("/getSection/{sid}")
    public Result getSectionList(@PathVariable Integer sid){
        List<Section> sections = sectionService.selectSectionList(sid);
        sections.get(0).setScount(sections.get(0).getWzList().size());
        return Result.ok(sections);
    }
}
