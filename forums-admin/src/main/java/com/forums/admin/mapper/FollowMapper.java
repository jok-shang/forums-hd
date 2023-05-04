package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.model.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:29
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    Page<Follow> followList(@Param("page")Page<Follow> page,@Param("userId")String userId);

    // 统计用户关注数
    Integer followCount(String userId);

    // 取消关注
    Integer cancelFollow(String userId,String followedId);
}
