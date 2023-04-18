package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.Section;
import com.forums.model.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/18 15:37
 */
@Service
public interface SectionService extends IService<Section> {


    List<Section> selectSectionList(Integer sid);
}
