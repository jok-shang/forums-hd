package com.forums.admin.controller.section;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.admin.service.SectionService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.Section;
import com.forums.model.pojo.WenZhang;
import com.forums.model.result.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private WenZhangService wenZhangService;


    /**
     * 获取文章板块列表计算板块下的文章数
     * @return result
     */
    @GetMapping("/getSectionAndWzCount")
    public Result getSectionAndWzCount(){
        List<Section> list = sectionService.list();
        List<Integer> list1 = new ArrayList<>();
        for (Section section : list){
            list1.add(section.getSid());
        }
        for (int i = 0;i<list.size();i++){
            UpdateWrapper<Section> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("sid",list1.get(i));
            QueryWrapper<WenZhang> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sid",list1.get(i));
            int count = wenZhangService.count(queryWrapper);
//            System.out.println(count+"=======******");
            updateWrapper.set("scount",count);
            sectionService.update(updateWrapper);
        }
        List<Section> list2 = sectionService.list();
        return Result.ok(list2);
    }
    /**
     * 获取分类列表下的文章集合和文章数
     * @param sid sid
     * @return Result
     */
    @GetMapping("/getSection/{sid}")
    public Result getSectionList(@PathVariable Integer sid){
        List<Section> sections = sectionService.selectSectionList(sid);
        // 判断sections集合的边界不为空再获取集合的大小！！！！ 不然报错索引异常
//        if (sections != null && sections.size() > 0) {
//            sections.get(0).setScount(sections.get(0).getWzList().size());
            return Result.ok(sections);
//        }
//        return Result.fail().message("没有找到文章呦~");
    }
}
