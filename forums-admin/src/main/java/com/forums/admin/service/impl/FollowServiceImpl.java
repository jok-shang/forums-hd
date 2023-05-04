package com.forums.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forums.admin.mapper.FansMapper;
import com.forums.admin.mapper.FollowMapper;
import com.forums.admin.service.FollowService;
import com.forums.model.pojo.Fans;
import com.forums.model.pojo.Follow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/4/27 12:41
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Resource
    private FollowMapper followMapper;
    @Resource
    private FansMapper fansMapper;
    /**
     * 关注操作
     * @param userId  当前登录用户
     * @param folleredUser   被关注的用户id
     * @return
     */
    @Override
    public boolean follow(String userId, String folleredUser) {
        // 向关注表添加数据
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFolleredUser(folleredUser);
        int insert = followMapper.insert(follow);
        // 向粉丝表中添加数据
        Fans fans = new Fans();
        fans.setUserId(folleredUser);
        fans.setFollowerId(userId);
        int insert1 = fansMapper.insert(fans);
        return insert > 0;
    }

    @Override
    public Page<Follow> followList(Page<Follow> page, String userId) {
        Page<Follow> allUserList = followMapper.followList(page,userId);
        return allUserList;
    }

    @Override
    public boolean pdFollowFlog(String userId, String followId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("followed_user",followId);
        Integer integer = followMapper.selectCount(queryWrapper);
        return integer > 0;
    }

    @Override
    public boolean cancelFollow(String userId, String followedId) {
        Integer integer = followMapper.cancelFollow(userId, followedId);
        return integer > 0;
    }
}
