package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.Study;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/20 19:42
 */
@Service
public interface StudyService extends IService<Study> {
    // 根据数据id查询子数据列表
    List<Study> findChlidData(Integer id);
}
