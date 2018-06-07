package com.code.edu.mapper;

import com.code.edu.dto.EduSecurityDto;
import com.code.edu.model.EduSecurity;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EduSecurityMapper extends BaseMapper<EduSecurity> {
    @Select("select * from edu_security where is_deleted = 0")
    @ResultMap("BaseResultDtoMap")
    List<EduSecurityDto> selectEnableAll();

    @Select("select * from edu_security where is_deleted = 0 order by oid")
    @ResultMap("BaseResultMap")
    List<EduSecurity> selectAll();
//    int deleteByPrimaryKey(Long id);
//
//    int insert(EduSecurity record);
//
//    int insertSelective(EduSecurity record);
//
//    EduSecurity selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(EduSecurity record);
//
//    int updateByPrimaryKey(EduSecurity record);
}