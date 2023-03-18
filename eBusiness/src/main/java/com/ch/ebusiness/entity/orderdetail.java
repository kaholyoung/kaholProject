package com.ch.ebusiness.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderdetail")
public class orderdetail {
    @TableId
    Integer id;
    @TableField("orderbasetable_id")
    Long orderbasetable_id;
    @TableField("goodstable_id")
    int goodstable_id;
    @TableField("shoppingnum")
    int shoppingnum;
}
