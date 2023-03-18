package com.ch.ebusiness.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("goodstype")
@Data
public class GoodsType {
	@TableId
	private Integer id;
	private String typename;

}
