package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableId(value = "uid",type = IdType.ASSIGN_UUID)
//    @TableId("uid")
    private String uid;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    /** 年龄 */
    @TableField("age")
    private Integer age;

    /** 性别 */
    @TableField("gender")
    private Integer gender;

    /** 爱好 */
    @TableField("hobby")
    private String hobby;

    /** 职业 */
    @TableField("job")
    private String job;

    /**  座右铭 */
    @TableField("motto")
    private String motto;

    /** 用户token */
    @TableField(exist = false)
    private String token;

    /**  用户验证码*/
    @TableField(exist = false)
    private String yzm;
}
