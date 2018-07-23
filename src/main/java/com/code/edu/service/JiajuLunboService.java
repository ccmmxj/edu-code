package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.JiajuLunbo;

import java.util.List;

public interface JiajuLunboService extends BaseService<JiajuLunbo,Long> {
    JiajuLunbo delLunbo(Long id, Long companyId);
    List<JiajuLunbo> findLunboAll(Long companyId);
    JiajuLunbo findOne(Long id, Long companyId);
    TableData<JiajuLunbo> findLunboTable(TableData<JiajuLunbo> tableData, Long companyId,String title);
    List<JiajuLunbo> findLunboByTypeAndComId(Long companyId, Byte type);
}
