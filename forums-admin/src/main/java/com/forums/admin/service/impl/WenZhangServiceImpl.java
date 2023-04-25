package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.DianZanMapper;
import com.forums.admin.mapper.ShouCangMapper;
import com.forums.admin.mapper.WenZhangMapper;
import com.forums.admin.service.DianZanService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.WenZhang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/4/18 18:00
 */
@Service
public class WenZhangServiceImpl extends ServiceImpl<WenZhangMapper, WenZhang> implements WenZhangService {

    @Resource
    private WenZhangMapper wenZhangMapper;
    @Resource
    private ShouCangMapper shouCangMapper;
    @Resource
    private DianZanMapper dianZanMapper;

    @Override
    public Page<WenZhang> getAll(Page<WenZhang> page) {
        Page<WenZhang> all = wenZhangMapper.getAll(page);
        return all;
    }

    @Override
    public Page<WenZhang> getAllBySid(Page<WenZhang> page, Integer sid) {
        Page<WenZhang> allBySid = wenZhangMapper.getAllBySid(page, sid);
        return allBySid;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean deleteWz(Integer tid) {
        dianZanMapper.deleteDianZanWz(tid);
        shouCangMapper.deleteShouCangWz(tid);
        Integer a = wenZhangMapper.deleteWz(tid);
        return a > 0;
    }
}
