package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.DianZan;
import com.forums.model.vo.DianZanVO;
import org.springframework.stereotype.Service;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:21
 */
@Service
public interface DianZanService extends IService<DianZan> {
    // 点赞操作
    boolean dianZan(DianZan dianZan);
}
