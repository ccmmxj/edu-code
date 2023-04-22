package com.code.edu.demo.mapper;

import com.code.edu.demo.anno.Query;
import com.code.edu.demo.anno.QueryMapper;
import org.apache.ibatis.annotations.Param;

@QueryMapper
public interface TestQueryMapper {

    @Query("id:{id} AND name:{name}")
    String test(@Param("id") String id, @Param("name") String name);
}
