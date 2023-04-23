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

    Page<DianZan> selectDianZanList(@Param("page") Page<DianZan> page,@Param("uid") Integer uid);
}
