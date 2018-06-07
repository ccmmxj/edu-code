package com.code.edu.mapper;

import com.code.edu.model.EduPersiom;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EduPersiomMapper extends BaseMapper<EduPersiom> {

    @Select("select ep.* from edu_role_persiom_related erpr join edu_persiom ep on ep.id = erpr.persiom_id and ep.is_deleted = 0 join edu_role er on erpr.role_id = er.id and er.is_deleted = 0 join edu_role_user_related erur on erur.role_id = er.id and erur.is_deleted = 0 join edu_user eu on eu.id = erur.user_id and eu.is_deleted = 0 where eu.id = #{userId} and erpr.is_deleted = 0")
    @ResultMap("BaseResultMap")
    List<EduPersiom> selectByUserId(@Param("userId") Long userId);

    @Select("select ep.* from edu_persiom ep join edu_persiom_security_related epsr on epsr.persiom_id = ep.id and epsr.is_deleted = 0 where ep.is_deleted = 0 and epsr.security_id = #{securityId}")
    @ResultMap("BaseResultMap")
    List<EduPersiom> selectBySecurityId(@Param("securityId") Long securityId);
}