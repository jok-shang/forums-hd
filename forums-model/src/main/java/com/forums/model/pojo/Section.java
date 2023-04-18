package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/18 15:01
 */
@TableName("section")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @TableId("sid")
    private Integer sid;

    @TableField("sname")
    private String sname;

    @TableField("sjianjie")
    private String sjianjie;

    @TableField("scount")
    private Integer scount;

    /** 文章集合 */
    @TableField(exist = false)
    private List<WenZhang> wzList;
}