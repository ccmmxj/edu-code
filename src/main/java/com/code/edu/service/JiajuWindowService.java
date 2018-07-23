package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.JiajuWindow;
import com.code.edu.model.JiajuWindow;

import java.util.List;

public interface JiajuWindowService extends BaseService<JiajuWindow,Long> {
    JiajuWindow delWindow(Long id, Long companyId);
    List<JiajuWindow> findWindowAll(Long companyId);
    JiajuWindow findOne(Long id, Long companyId);
    TableData<JiajuWindow> findWindowTable(TableData<JiajuWindow> tableData, Long companyId, String title);
    List<JiajuWindow> findWindowByTypeAndComId(Long companyId, Byte type);
}
