package com.ch.ebusiness.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    int sid;
    @TableId
    Integer id;

    int uid;
}
