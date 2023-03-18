package com.ch.ebusiness.repository.before;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.BUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BUserRepository extends BaseMapper<BUser> {
}
