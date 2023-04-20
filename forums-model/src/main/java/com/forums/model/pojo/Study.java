package com.forums.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/4/18 10:30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("study_direct")
public class Study {


    @TableId(value = "sid",type = IdType.AUTO)
    private Long sid;

    @TableField("sname")
    private String sname;

    @TableField("parent_id")
    private Long parentId;

    @TableField("value")
    private Long value;

    @TableField(exist = false)
    private List<Study> child = new ArrayList<>();

    @TableField(exist = false)
    private boolean hasChildren;

}
