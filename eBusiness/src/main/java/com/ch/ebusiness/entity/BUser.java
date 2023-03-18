package com.ch.ebusiness.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@TableName("busertable")
@Data
public class BUser {
	@TableId("id")
	private Integer id;
	@NotBlank(message="邮箱必须输入！")
	@Email(message="邮箱格式不正确！")
	@TableField("bemail")
	private String bemail;
	@NotBlank(message="密码必须输入！")
	@Length(min=6, max=20, message="密码长度在6到20之间！")

	@TableField("bpwd")
	private String bpwd;

	@TableField(exist = false)
	private String rebpwd;
	@TableField(exist = false)
	private String code;

}
