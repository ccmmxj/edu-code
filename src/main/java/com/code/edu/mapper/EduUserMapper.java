package com.code.edu.mapper;

import com.code.edu.model.EduUser;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EduUserMapper extends BaseMapper<EduUser>  {

    @Select("select * from edu_user where user_name = #{username}")
    @ResultMap("BaseResultMap")
    EduUser selectByUsername(@Param("username") String username);
}