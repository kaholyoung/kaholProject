package com.ch.ebusiness.repository.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsRepository extends BaseMapper<Goods> {
	Goods selectAGoods(Integer id);
	List<Goods> selectAllGoodsByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
	List<Map<String, Object>> selectFocusGoods(Integer id);
	List<Map<String, Object>> selectCartGoods(Integer id);
	List<Map<String, Object>> selectOrderGoods(Integer id);
}
