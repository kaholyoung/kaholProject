package com.ch.ebusiness.repository.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeRepository extends BaseMapper<GoodsType> {


//	int selectAll();
//	List<GoodsType> selectAllTypeByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
//	int deleteType(int id);
//	List<Goods> selectGoods(int goodstype_id);
//	int addType(GoodsType goodsType);
}
