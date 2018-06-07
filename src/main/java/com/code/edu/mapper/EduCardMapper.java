package com.code.edu.mapper;

import com.code.edu.model.EduCard;
import com.code.edu.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EduCardMapper extends BaseMapper<EduCard> {

    @Select("select * from edu_card where is_deleted = 0 and company_id = #{companyId}")
    List<EduCard> selectEnableAll(Long companyId);
    //    int save(EduCard t);
    //    int update(EduCard t);
    //    int saveOrUpdate(EduCard t);
    //    EduCard findOne(Long id);
    //    List<Long> findAll();
//    int insert(EduCard record);
//
//    int insertSelective(EduCard record);
//
//    EduCard selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(EduCard record);
//
//    int updateByPrimaryKey(EduCard record);
}