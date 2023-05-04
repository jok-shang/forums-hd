package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.model.pojo.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:38
 */
@Mapper
public interface FansMapper extends BaseMapper<Fans> {
    // 粉丝列表
    Page<Fans> fansList(@Param("page")Page<Fans> page,@Param("userId") String userId);

    // 统计粉丝数
    Integer fansCount(String userId);

    // 取消关注操作取消粉丝
    Integer cancelFans(String userId,String followedId);
}
