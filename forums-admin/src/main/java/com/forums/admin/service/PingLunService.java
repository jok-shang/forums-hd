package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.PingLun;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:27
 */

@Service
public interface PingLunService extends IService<PingLun> {

    List<PingLun> plList(Integer tid);

    boolean deletePL(Integer pid);
}
