package com.ch.ebusiness.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("focustable")
public class focustable {


    @TableId("id")
    Integer id;
    @TableField(value = "goodstable_id")
    int goodstable_id;
    @TableField(value = "busertable_id")
    int busertable_id;
    @TableField(value = "focustime")
    String focustime;


}
