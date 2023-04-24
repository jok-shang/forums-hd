package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.WenZhang;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @auther 尚智江
 * @Date 2023/4/18 17:59
 */
@Service
public interface WenZhangService extends IService<WenZhang> {

    // 查询文章按时间排序
    Page<WenZhang> getAll(Page<WenZhang> page);

    // 根据sid分类查询文章按时间排序
    Page<WenZhang> getAllBySid(Page<WenZhang> page,Integer sid);
}
