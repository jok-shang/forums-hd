package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.*;
import com.forums.admin.service.DianZanService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.WenZhang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

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
    @Resource
    private FollowMapper followMapper;
    @Resource
    private FansMapper fansMapper;

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

    @Override
    public HashMap<String, Integer> getUserCount(String userId) {
        // 用户关注数
        Integer integer = followMapper.followCount(userId);
        // 用户粉丝数
        Integer integer1 = fansMapper.fansCount(userId);
        // 用户获赞总数
        Integer integer2 = wenZhangMapper.userStartNum(userId);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("followCount",integer);
        map.put("fansCount",integer1);
        map.put("starCount",integer2);
        return map;
    }
}
