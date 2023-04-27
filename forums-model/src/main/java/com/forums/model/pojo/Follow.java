package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther 尚智江
 * @Date 2023/4/26 22:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {

    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //  当前登录用户id
    @TableField("user_id")
    private Integer userId;

    // 被关注用户id
    @TableField("followed_user")
    private Integer folleredUser;

    // 状态 是否取关  默认0 关注   1 取消关注
    @TableField("status")
    private Integer status;
}
