package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther 尚智江
 * @Date 2023/4/20 23:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dianzan")
public class DianZan {

    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    @TableField("uid")
    private Integer uid;

    @TableField("tid")
    private Integer tid;

    @TableField("createTime")
    private Date createTime;

}
