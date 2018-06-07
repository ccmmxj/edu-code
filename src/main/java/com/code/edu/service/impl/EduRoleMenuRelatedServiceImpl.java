package com.code.edu.service.impl;

import com.code.edu.mapper.EduRoleMenuRelatedMapper;
import com.code.edu.model.EduRoleMenuRelated;
import com.code.edu.service.EduRoleMenuRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduRoleMenuRelatedServiceImpl implements EduRoleMenuRelatedService {
    @Autowired
    private EduRoleMenuRelatedMapper eduRoleMenuRelatedMapper;

    @Override
    public EduRoleMenuRelated findOne(Long id) {
        return eduRoleMenuRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduRoleMenuRelated eduRoleMenuRelated) {
        if(eduRoleMenuRelated.getId() != null) return update(eduRoleMenuRelated);
        return save(eduRoleMenuRelated);
    }

    @Override
    public int save(EduRoleMenuRelated eduRoleMenuRelated) {
        return eduRoleMenuRelatedMapper.insertSelective(eduRoleMenuRelated);
    }

    @Override
    public int update(EduRoleMenuRelated eduRoleMenuRelated) {
        return eduRoleMenuRelatedMapper.updateByPrimaryKeySelective(eduRoleMenuRelated);
    }

    @Override
    public List<EduRoleMenuRelated> findAll() {
        return eduRoleMenuRelatedMapper.selectAll();
    }
}
