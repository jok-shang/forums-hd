package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther 尚智江
 * @Date 2023/4/21 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pinglun")
public class PingLun {

    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    @TableField("tid")
    private Integer tid;

    @TableField("uid")
    private Integer uid;

    @TableField("pneirong")
    private String pNeiRong;

    @TableField("createtime")
    private String createTimel;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "isdelete", fill = FieldFill.INSERT)
    private Integer isDelete;
}
