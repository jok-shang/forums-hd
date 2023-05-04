package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/21 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("pinglun")
public class PingLun {

    /*
     * 二级评论
     */

    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    @TableField("tid")
    private Integer tid;

    @TableField("uid")
    private String uid;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("pneirong")
    private String pneirong;

    @TableField("createtime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private User user;

//    @TableField(exist = false)
//    private List<PingLun> pingLunList;



}
