package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther 尚智江
 * @Date 2023/4/17 20:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userinfo")
public class User {

    /** 用户id */
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;

    /** 用户名 */
    @TableField("username")
    private String username;

    /** 用户密码 */
    @TableField("password")
    private String password;

    /** 用户邮箱 */
    @TableField("email")
    private String email;

    /** 用户创建时间 */
    @TableField(value = "creattime",fill = FieldFill.INSERT)
    private Date createTime;

    /** 用户头像链接 */
    @TableField("headImage")
    private String headImage;

    /** 用户是否销户 */
    @TableLogic(value = "0",delval = "1")
    @TableField(value = "isdelete",fill = FieldFill.INSERT)
    private Integer isDelete;

    /** md5加密盐值 */
    @TableField("salt")
    private String salt;

    /** 用户token */
    @TableField(exist = false)
    private String token;

    /**  用户验证码*/
    @TableField(exist = false)
    private String yzm;
}
