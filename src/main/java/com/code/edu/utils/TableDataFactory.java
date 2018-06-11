package com.code.edu.utils;

import com.code.edu.dto.TableData;
import com.github.pagehelper.PageInfo;

import java.util.List;

public final class TableDataFactory {
    private TableDataFactory() {
        super();
    }
    public static <T> TableData<T> newInstaceSuccessResult(TableData<T> tableData,PageInfo<T> page,List<T> rows){
        return newInstaceResult(tableData,page,rows,200L,"获取成功");
    }
    public static <T> TableData<T> newInstaceFailResult(TableData<T> tableData,PageInfo<T> page,List<T> rows){
        return newInstaceResult(tableData,page,rows,500L,"获取失败");
    }

    public static <T> TableData<T> newInstaceResult(TableData<T> tableData,PageInfo<T> page,List<T> rows,Long code,String msg){
        TableData<T> tTableData = tableData.copyTableData();
        tTableData.setTotal((int)page.getTotal());
        tTableData.setRows(rows);
        tTableData.setCode(code);
        tTableData.setMsg(msg);
        return tTableData;
    }
}
