package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduAdvert;

import java.util.List;

public interface EduAdvertService extends BaseService<EduAdvert,Long> {
    EduAdvert delAdvert(Long id);
    List<EduAdvert> findAdvertAll();
    List<EduAdvert> findByType(Byte type);
    EduAdvert findOne(Long id);
    TableData<EduAdvert> findAdvertTable(TableData<EduAdvert> tableData, String name);
}
