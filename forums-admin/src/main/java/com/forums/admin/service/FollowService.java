package com.forums.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forums.model.pojo.Follow;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:40
 */
@Service
public interface FollowService extends IService<Follow> {

    // 关注操作
    boolean follow(Integer userId,Integer folleredUser);

    // 关注列表
    Page<Follow> followList(Page<Follow> page,Integer userId);

    // 判断是否关注
    boolean pdFollowFlog(Integer userId,Integer followId);

    boolean cancelFollow(Integer userId,Integer followedId);
}
