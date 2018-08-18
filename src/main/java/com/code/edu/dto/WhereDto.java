package com.code.edu.dto;

import tk.mybatis.mapper.util.Sqls;

public class WhereDto {
    private WhereDto(){

    }
    public static Sqls defaultWhere(Long companyId){
        return Sqls.custom().andEqualTo("companyId",companyId).andEqualTo("isDeleted",0);
    }
    public static Sqls notInCompanyIdWhere(){
        return Sqls.custom().andEqualTo("isDeleted",0);
    }
}
