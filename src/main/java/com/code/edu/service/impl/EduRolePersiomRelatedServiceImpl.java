package com.code.edu.service.impl;

import com.code.edu.mapper.EduRolePersiomRelatedMapper;
import com.code.edu.model.EduRolePersiomRelated;
import com.code.edu.service.EduRolePersiomRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduRolePersiomRelatedServiceImpl implements EduRolePersiomRelatedService {
    @Autowired
    private EduRolePersiomRelatedMapper eduRolePersiomRelatedMapper;

    @Override
    public EduRolePersiomRelated findOne(Long id) {
        return eduRolePersiomRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduRolePersiomRelated eduRolePersiomRelated) {
        if(eduRolePersiomRelated.getId() != null) return update(eduRolePersiomRelated);
        return save(eduRolePersiomRelated);
    }

    @Override
    public int save(EduRolePersiomRelated eduRolePersiomRelated) {
        return eduRolePersiomRelatedMapper.insertSelective(eduRolePersiomRelated);
    }

    @Override
    public int update(EduRolePersiomRelated eduRolePersiomRelated) {
        return eduRolePersiomRelatedMapper.updateByPrimaryKeySelective(eduRolePersiomRelated);
    }

    @Override
    public List<EduRolePersiomRelated> findAll() {
        return eduRolePersiomRelatedMapper.selectAll();
    }
}
