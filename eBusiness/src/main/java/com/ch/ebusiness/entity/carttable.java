package com.ch.ebusiness.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("carttable")
public class carttable {

    @TableId("id")
    Integer id;


    int buserable_id;
    int goodstable_id;
    int shoppingnum;


}
