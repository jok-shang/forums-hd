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
 * @Date 2023/4/18 15:02
 */
@TableName("wenzhang")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WenZhang {

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    @TableField("uid")
    private String uid;

    @TableField("sid")
    private Integer sid;

    @TableField("biaoti")
    private String biaoTi;

    @TableField("jianjie")
    private String jianjie;

    @TableField("neirong")
    private String neiRong;

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "isdelete", fill = FieldFill.INSERT)
    private Integer isDelete;

    @TableField("tstart")
    private Integer tStart;

    @TableField("tshou")
    private Integer tShou;

    @TableField(exist = false)
    private Section section;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private boolean StartFlag;

    @TableField(exist = false)
    private boolean ShouFlag;


//
//    @TableField(exist = false)
//    private User user;
//    @TableField(exist = false)
//    private List<PingLun> pingLunList;
}