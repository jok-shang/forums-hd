package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.ShouCangMapper;
import com.forums.admin.mapper.WenZhangMapper;
import com.forums.admin.service.ShouCangService;
import com.forums.admin.service.WenZhangService;
import com.forums.model.pojo.ShouCang;
import com.forums.model.pojo.WenZhang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:24
 */
@Service
public class ShouCangServiceImpl extends ServiceImpl<ShouCangMapper, ShouCang> implements ShouCangService {

    @Resource
    private WenZhangMapper wenZhangMapper;
    @Resource
    private ShouCangMapper shouCangMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean shoucang(ShouCang shouCang) {
        WenZhang wenZhang = wenZhangMapper.selectById(shouCang.getTid());
        if (shouCang.getCount() > 0){
            Integer tshou = wenZhang.getTShou() + shouCang.getCount();
            // 根据 tid 修改文章的收藏数
            UpdateWrapper<WenZhang> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("tid",shouCang.getTid());
            updateWrapper.set("tshou",tshou);
            int update = wenZhangMapper.update(wenZhang, updateWrapper);
            System.out.println(update+"------======");
            // 向收藏表插入数据
            shouCang.setCreateTime(new Date());
            int insert = shouCangMapper.insert(shouCang);
            return insert > 0;
        }
        if (shouCang.getCount() < 0 ){
            Integer tshou = wenZhang.getTShou() + shouCang.getCount();
            UpdateWrapper<WenZhang> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("tid",shouCang.getTid());
            updateWrapper.set("tshou",tshou);
            int update = wenZhangMapper.update(wenZhang, updateWrapper);
            // 删除点赞表中的数据
            QueryWrapper<ShouCang> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tid",wenZhang.getTid());
            queryWrapper.eq("uid", shouCang.getUid());
            ShouCang shouCang1 = shouCangMapper.selectOne(queryWrapper);
            int i = shouCangMapper.deleteById(shouCang1.getCid());
            return i > 0;
        }
        return false;
    }

    // 返回用户收藏列表
    @Override
    public Page<ShouCang> selectShouCangList(Page<ShouCang> page, String uid) {
        Page<ShouCang> shouCangPage = shouCangMapper.selectShouCangList(page,uid);
        return shouCangPage;
    }

    /*
    返回true说明用户已经点赞过
    false说明用户未点过或者已经取消点赞
     */
    @Override
    public boolean isToShou(String uid,Integer tid) {
        Integer count = shouCangMapper.getCountS(uid, tid);
        System.out.println(count);
        return count > 0;
    }

}
