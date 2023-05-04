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
 * @Date 2023/4/27 12:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fans")
public class Fans {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("follower_id")
    private String followerId;

    @TableField(exist = false)
    private List<User> userList;

    @TableField("status")
    private Integer status;
}
