package com.code.edu.service.impl;

import com.code.edu.mapper.EduRoleUserRelatedMapper;
import com.code.edu.model.EduRoleUserRelated;
import com.code.edu.service.EduRoleUserRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduRoleUserRelatedServiceImpl implements EduRoleUserRelatedService {
    @Autowired
    private EduRoleUserRelatedMapper eduRoleUserRelatedMapper;

    @Override
    public EduRoleUserRelated findOne(Long id) {
        return eduRoleUserRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduRoleUserRelated eduRoleUserRelated) {
        if(eduRoleUserRelated.getId() != null) return update(eduRoleUserRelated);
        return save(eduRoleUserRelated);
    }

    @Override
    public int save(EduRoleUserRelated eduRoleUserRelated) {
        return eduRoleUserRelatedMapper.insertSelective(eduRoleUserRelated);
    }

    @Override
    public int update(EduRoleUserRelated eduRoleUserRelated) {
        return eduRoleUserRelatedMapper.updateByPrimaryKeySelective(eduRoleUserRelated);
    }

    @Override
    public List<EduRoleUserRelated> findAll() {
        return eduRoleUserRelatedMapper.selectAll();
    }
}
