package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer uid;

    @TableField("tid")
    private Integer tid;

    @TableField("create_time")
    private Date createTime;


}
