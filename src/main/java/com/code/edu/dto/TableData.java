package com.code.edu.dto;

import java.util.List;

/**
 * Created by wb-cmx239369 on 2017/4/1.
 */
public class TableData<T> {
    private T t;
    private Long code;
    private String msg;
    private List<T> rows;
    private Integer total;
    private Integer pageSize;
    private Integer pageNumber;
    private String sortName;
    private String sortOrder;
    private String searchText;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public TableData<T> copyTableData(){
        TableData<T> tableData = new TableData<>();
        tableData.pageNumber = this.pageNumber;
        tableData.pageSize = this.pageSize;
        tableData.rows = this.rows;
        tableData.searchText = this.searchText;
        tableData.sortName = this.sortName;
        tableData.sortOrder = this.sortOrder;
        tableData.total = this.total;
        tableData.code = this.code;
        tableData.msg = this.msg;
        tableData.t = this.t;
        return tableData;
    }
}
