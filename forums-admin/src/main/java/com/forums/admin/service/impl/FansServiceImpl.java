package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.FansMapper;
import com.forums.admin.service.FansService;
import com.forums.model.pojo.Fans;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:45
 */
@Service
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {

    @Resource
    private FansMapper fansMapper;

    @Override
    public Page<Fans> fansList(Page<Fans> page, String userId) {
        Page<Fans> fansPage = fansMapper.fansList(page, userId);
        return fansPage;
    }

    @Override
    public boolean cancelFans(String userId, String followedId) {
        Integer integer = fansMapper.cancelFans(userId, followedId);
        return integer > 0;
    }
}
