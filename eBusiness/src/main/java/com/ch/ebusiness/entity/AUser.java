package com.ch.ebusiness.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("ausertable")
public class AUser {
	private String aname;
	private String apwd;

}
