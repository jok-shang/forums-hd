package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.model.pojo.ShouCang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:07
 */
@Mapper
public interface ShouCangMapper extends BaseMapper<ShouCang> {
    // 用户收藏列表返回
    Page<ShouCang> selectShouCangList(@Param("page") Page<ShouCang> page,@Param("uid") String uid);

    // 删除收藏列表关于此篇文章的记录
    Integer deleteShouCangWz(Integer tid);

    // 统计用户是否点过赞
    Integer getCountS(@Param("uid") String uid,@Param("tid") Integer tid);
}
