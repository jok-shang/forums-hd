package com.forums.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forums.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUsername(Long uid);

    // 根据用户id查询信息用于展示
    User getUserByUid(String uid);
}
