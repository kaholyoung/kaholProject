package com.ch.ebusiness.repository.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.ebusiness.entity.AUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRepository extends BaseMapper<AUser> {
//	List<AUser> login(AUser aUser);

    @Select("select * from ausertable where aname = #{aname} and apwd = #{apwd}")
    List <AUser> login(AUser aUser);
}
