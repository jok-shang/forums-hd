package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.PingLunMapper;
import com.forums.admin.mapper.UserMapper;
import com.forums.admin.service.PingLunService;
import com.forums.model.pojo.PingLun;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @auther 尚智江
 * @Date 2023/4/21 21:28
 */
@Service
public class PingLunServiceImpl extends ServiceImpl<PingLunMapper, PingLun> implements PingLunService {

    @Resource
    private PingLunMapper pingLunMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public List<PingLun> plList(Integer tid) {
        return pingLunMapper.selectPL(tid);
    }

    @Override
    public boolean deletePL(Integer pid) {
        Integer b = pingLunMapper.deletePL(pid);
        return b > 0;
    }
}
