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
 * @Date 2023/4/20 23:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shoucang")
public class ShouCang {

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @TableField("uid")
    private String uid;

    @TableField("tid")
    private Integer tid;

    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private Integer count;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private WenZhang wenZhang;

}
