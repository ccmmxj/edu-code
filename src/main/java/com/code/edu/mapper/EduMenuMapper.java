package com.code.edu.mapper;

import com.code.edu.dto.EduMenuDto;
import com.code.edu.model.EduMenu;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EduMenuMapper extends BaseMapper<EduMenu> {
    @Select("select em.* from edu_role_menu_related ermr join edu_role er on ermr.role_id = er.id join edu_menu em on em.id = ermr.menu_id join edu_role_user_related erur on erur.role_id = er.id join edu_user eu on eu.id = erur.user_id where eu.id = #{userId} and em.parent_id = 0")
    @ResultMap("BaseResultDtoMap")
    List<EduMenuDto> selectByUserId(@Param("userId") Long userId);


    @Select("select em.* from edu_menu em where em.parent_id = #{pid}")
    @ResultMap("BaseResultDtoMap")
    List<EduMenuDto> selectByPid(@Param("pid") Long pid);
}