package com.code.edu.service;

import com.code.edu.dto.TableData;
import com.code.edu.model.Resource;

public interface EduOtherService {
    int clearResource();

    TableData<Resource> findDelResourceTable(TableData<Resource> tableData, String title);

    String restoreResource(String url);
}
