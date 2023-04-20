package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.StudyMapper;
import com.forums.admin.service.StudyService;
import com.forums.model.pojo.Study;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/20 19:43
 */
@Service
public class StudyServiceImpl extends ServiceImpl<StudyMapper, Study> implements StudyService {


    @Override
    public List<Study> findChlidData(Integer id) {
        QueryWrapper<Study> queryWrapper = new QueryWrapper<>();
        // 判断parent_id = 传入id值
        queryWrapper.eq("parent_id",id);
        // 查询 id 的 List
        List<Study> studyList = baseMapper.selectList(queryWrapper);
        // 向list集合中每个study对象设置hasChildren值
        for (Study study: studyList){
            Long studyId = study.getSid();
            boolean isChildren = this.isChildren(studyId);
            study.setHasChildren(isChildren);
        }
        return studyList;
    }

    // 判断下面是否带有子节点
    private boolean isChildren(Long id){
        QueryWrapper<Study> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
