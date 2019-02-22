package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.enums.ConfigTypeEnum;
import com.code.edu.model.EduConfig;

import java.util.List;

public interface EduConfigService extends BaseService<EduConfig,Long> {
    EduConfig delConfig(Long id,Long companyId);
    TableData<EduConfig> findConfigTable(TableData<EduConfig> tableData, Long companyId, String title,ConfigTypeEnum type);
    EduConfig findOne(Long id, Long companyId);
    List<EduConfig> findConfigByTypeAndComId(Long companyId, String type);
}
