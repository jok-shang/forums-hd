package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.Fans;
import org.springframework.stereotype.Service;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:45
 */
@Service
public interface FansService extends IService<Fans> {

    Page<Fans> fansList(Page<Fans> page,String userId);

    boolean cancelFans(String userId,String followedId);
}
