package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/26 22:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("follow")
public class Follow {

    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //  当前登录用户id
    @TableField("user_id")
    private String userId;

    // 被关注用户id
    @TableField("followed_user")
    private String folleredUser;

    // 被关注用户列表
    @TableField(exist = false)
    private List<User> userList;

    // 状态 是否取关  默认0 关注   1 取消关注
    @TableField("status")
    private Integer status;
}
