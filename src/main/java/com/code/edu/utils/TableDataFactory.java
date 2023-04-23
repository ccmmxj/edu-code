package com.code.edu.utils;

import com.code.edu.dto.TableData;
import com.github.pagehelper.PageInfo;

import java.util.List;

public final class TableDataFactory {
    private TableDataFactory() {
        super();
    }
    public static <T> TableData<T> newInstaceSuccessResult(TableData<T> tableData,PageInfo<T> page,List<T> rows){
        return newInstaceSuccessResult(tableData, page.getTotal(), rows);
    }
    public static <T> TableData<T> newInstaceSuccessResult(TableData<T> tableData, long total, List<T> rows){
        return newInstaceResult(tableData, total, rows,200L,"获取成功");
    }
    public static <T> TableData<T> newInstaceFailResult(){
        return newInstaceResult(null, -1, null,500L,"获取失败");
    }

    public static <T> TableData<T> newInstaceResult(TableData<T> tableData, long total, List<T> rows,Long code,String msg){
        TableData<T> tTableData = tableData.copyTableData();
        tTableData.setTotal((int)total);
        tTableData.setRows(rows);
        tTableData.setCode(code);
        tTableData.setMsg(msg);
        return tTableData;
    }
}
