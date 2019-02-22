package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduConfig;

public interface EduConfigService extends BaseService<EduConfig,Long> {
    EduConfig delConfig(Long id,Long companyId);
    TableData<EduConfig> findConfigTable(TableData<EduConfig> tableData, Long companyId, String title);
    EduConfig findOne(Long id, Long companyId);
}
