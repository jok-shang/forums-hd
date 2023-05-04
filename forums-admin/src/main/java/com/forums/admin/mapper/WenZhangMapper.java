package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forums.model.pojo.DianZan;
import com.forums.model.pojo.WenZhang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther 尚智江
 * @Date 2023/4/18 18:01
 */
@Mapper
public interface WenZhangMapper extends BaseMapper<WenZhang> {

    // 查询所有文章按时间排序
    Page<WenZhang> getAll(@Param("page") Page<WenZhang> page);

    // 根据sid分类查询文章按时间排序
    Page<WenZhang> getAllBySid(@Param("page") Page<WenZhang> page,@Param("sid") Integer sid);

    // 用户删除自己发表的文章
    Integer deleteWz(Integer tid);

    // 统计用户文章获赞总数
    Integer userStartNum(String userId);
}
