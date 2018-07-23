package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduCard;

import java.util.List;

public interface EduCardService extends BaseService<EduCard,Long> {
    EduCard delCard(Long id,Long companyId);
    List<EduCard> findCardAll(Long companyId);
    EduCard findOne(Long id,Long companyId);
    TableData<EduCard> findCardTable(TableData<EduCard> tableData,Long companyId,String title);
    List<EduCard> findCardByTypeAndComId(Long companyId,Byte type);
}
