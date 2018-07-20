package com.code.edu.mapper;

import com.code.edu.model.EduRole;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EduRoleMapper extends BaseMapper<EduRole> {
    @Select("SELECT er.* FROM edu_role_user_related erur JOIN edu_role er ON erur.role_id = er.id AND er.is_deleted = 0 JOIN edu_user eu ON eu.id = erur.user_id AND eu.is_deleted = 0 WHERE eu.id = #{userId} AND erur.is_deleted = 0" )
    @ResultMap("BaseResultMap")
    List<EduRole> selectByUserId(@Param("userId") Long userId);
}