package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.DianZanMapper;
import com.forums.admin.mapper.WenZhangMapper;
import com.forums.admin.service.DianZanService;
import com.forums.model.pojo.DianZan;
import com.forums.model.pojo.WenZhang;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 21:22
 */
@Service
public class DianZanServiceImpl extends ServiceImpl<DianZanMapper, DianZan> implements DianZanService {

    @Resource
    private WenZhangMapper wenZhangMapper;
    @Resource
    private DianZanMapper dianZanMapper;
    /**
     * 用户点赞操作
     *
     *     private Integer did;   点赞id
     *     private Integer tid;   文章id
     *     private Integer uid;   点赞用户id
     *     private Date createTime;  点赞时间
     *     // 用于判断是第一次还是第二次
     *     private Integer count;   0、1
     *
     * @param dianZan
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean dianZan(DianZan dianZan) {
        /*
        判断用户是否是第一次点赞
        修改当前文章下的点赞个数  +1
        向点赞表添加数据
         */

        // 根据tid查询文章内容
        WenZhang wenZhang = wenZhangMapper.selectById(dianZan.getTid());
//        System.out.println("根据id查询文章内容========"+wenZhang);
        if (dianZan.getCount() > 0){
            // 获取当前文章的点赞数 + 前端传的count
            Integer tstart = wenZhang.getTStart() + dianZan.getCount();
            // 根据 tid 修改文章的点赞数
            UpdateWrapper<WenZhang> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("tid",dianZan.getTid());
            updateWrapper.set("tstart",tstart);
            int update = wenZhangMapper.update(wenZhang,updateWrapper);
            // 向点赞表中插入数据
            dianZan.setCreateTime(new Date());
            int insert = dianZanMapper.insert(dianZan);
            return insert > 0;
        }
        /*
        判断用户是否是第二次点赞
        修改当前文章下的点赞个数  -1
        删除点赞表中的数据
         */
        if (dianZan.getCount() < 0){
            Integer tstart = wenZhang.getTStart() + dianZan.getCount();
            UpdateWrapper<WenZhang> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("tid",dianZan.getTid());
            updateWrapper.set("tstart",tstart);
            int update = wenZhangMapper.update(wenZhang, updateWrapper);
            // 删除点赞表的数据
            QueryWrapper<DianZan> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tid",wenZhang.getTid()).eq("uid",dianZan.getUid());
            DianZan dianZan1 = dianZanMapper.selectOne(queryWrapper);
            int i = dianZanMapper.deleteById(dianZan1.getDid());
            return i > 0;
        }
        return true;
    }



    @Override
    public Page<DianZan> selectDianZanList(Page<DianZan> page,String uid) {
        Page<DianZan> dianZans = dianZanMapper.selectDianZanList(page,uid);
        return dianZans;
    }

    /*
    返回true说明用户已经点赞过
    false说明用户未点过或者已经取消点赞
     */
    @Override
    public boolean isToStart(String uid,Integer tid) {
        Integer count = dianZanMapper.getCount(uid,tid);
        System.out.println(count);
        return count > 0;
    }
}
