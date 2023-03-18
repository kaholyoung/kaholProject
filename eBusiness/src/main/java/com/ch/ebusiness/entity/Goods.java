package com.ch.ebusiness.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("goodstable")

public class Goods {
	@TableId
	private Integer id;


	private String gname;
	private double goprice;
	private double grprice;
	private int gstore;
	private String  gpicture;
	@TableField(value = "goodstype_id")
	private int goodstype_id;
	@TableField(value = "isAdvertisement")
	private int isAdvertisement;
	@TableField(value = "isRecommend")
	private int isRecommend;


	@TableField(exist = false)
	private MultipartFile fileName;
	@TableField(exist = false)
	private String typename;
	@TableField(exist = false)
	private int buyNumber;//加入购物车使用


	@Version
	private Integer version;

}
