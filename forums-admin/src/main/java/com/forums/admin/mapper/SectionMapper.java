package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forums.model.pojo.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/18 15:03
 */
@Mapper
public interface SectionMapper extends BaseMapper<Section> {

    /*
    根据分类的id查询分类文章列表
     */
    List<Section> selectSectionList(Integer sid);
}
