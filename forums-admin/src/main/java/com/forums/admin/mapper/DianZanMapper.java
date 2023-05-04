package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.model.pojo.DianZan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:04
 */
@Mapper
public interface DianZanMapper extends BaseMapper<DianZan> {

    // 用户点赞列表返回
    Page<DianZan> selectDianZanList(@Param("page") Page<DianZan> page,@Param("uid") String uid);

    // 统计用户是否点过赞
    Integer getCount(@Param("uid") String uid,@Param("tid") Integer tid);

    // 发表文章的用户删除自己文章移除所有点赞过的列表
    Integer deleteDianZanWz(Integer tid);
}
