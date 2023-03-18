package com.ch.ebusiness.entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("orderbasetable")
@Data
public class Order {
	@TableId
	@TableField("id")
	private Long id;

	@TableField("busertable_id")
	private Integer busertable_id;
	@TableField("amount")
	private Double amount;
	@TableField("status")
	private Integer status;
	@TableField("orderdate")
	private String orderdate;



}
