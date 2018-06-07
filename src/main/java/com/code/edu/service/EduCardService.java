package com.code.edu.service;

import com.code.edu.model.EduCard;

import java.util.List;

public interface EduCardService extends BaseService<EduCard,Long> {
    EduCard delCard(Long id,Long companyId);
    List<EduCard> findCardAll(Long companyId);
}
