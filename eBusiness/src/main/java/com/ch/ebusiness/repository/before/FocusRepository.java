package com.ch.ebusiness.repository.before;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.Order;
import com.ch.ebusiness.entity.carttable;
import com.ch.ebusiness.entity.focustable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FocusRepository extends BaseMapper<focustable> {

}
