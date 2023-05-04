package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.DianZan;
import com.forums.model.vo.DianZanVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:21
 */
@Service
public interface DianZanService extends IService<DianZan> {
    // 点赞操作
    boolean dianZan(DianZan dianZan);

    // 用户查看自己点赞的列表
    Page<DianZan> selectDianZanList(Page<DianZan> page,String uid);

    // 判断当前登录用户是否点赞
    boolean isToStart(@Param("uid") String uid,@Param("tid") Integer tid);
}
