package com.ch.ebusiness.repository.before;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.carttable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ch.ebusiness.entity.Order;

@Mapper
public interface CartRepository extends BaseMapper<carttable> {
	public List<Map<String,Object>> isFocus(@Param("uid") Integer uid, @Param("gid") Integer gid);
	public int focus(@Param("uid") Integer uid, @Param("gid") Integer gid);
	public int putCart(@Param("uid") Integer uid,
					   @Param("gid") Integer gid,
					   @Param("bnum") Integer bnum);
	public List<Map<String,Object>>  isPutCart(@Param("uid") Integer uid, @Param("gid") Integer gid);
	public int updateCart(@Param("uid") Integer uid,
						  @Param("gid") Integer gid,
						  @Param("bnum") Integer bnum);
	public List<Map<String,Object>>  selectCart(Integer uid);
	public int deleteAgoods(@Param("uid") Integer uid, @Param("gid") Integer gid);
	public int clear(Integer uid);
	public int addOrder(Order order);

	/**
	 * TODO: 原本为Integer 我改为Long
	 * @param ordersn  原本为Integer 我改为Long
	 * @param uid
	 * @return
	 */
	public int addOrderDetail(@Param("ordersn") Long ordersn, @Param("uid") Integer uid);
	public List<Map<String,Object>> selectGoodsShop(Integer uid);
	public int updateStore(Map<String,Object> map);

	/**
	 * TODO: 原本为Integer 我改为Long
	 * @param ordersn  原本为Integer 我改为Long
	 * @return
	 */
	public int pay(Long ordersn);
	public List<Map<String,Object>> myFocus(Integer uid);
	public List<Map<String,Object>> myOrder(Integer uid);
	public List<Map<String,Object>> orderDetail(Integer id);
	public int updateUpwd(@Param("uid") Integer uid, @Param("bpwd") String bpwd);
}
