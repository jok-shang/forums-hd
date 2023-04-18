package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther 尚智江
 * @Date 2023/4/18 10:30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("study_direct")
public class Study {
    @TableId
    private Long sid;

    @TableField("sname")
    private String sname;

    @TableField("parent_id")
    private Long parentId;

    @TableField("value")
    private Long value;

    @TableField(exist = false)
    private boolean hasChildren;

}