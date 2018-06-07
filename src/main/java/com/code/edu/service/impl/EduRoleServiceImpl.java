package com.code.edu.service.impl;

import com.code.edu.mapper.EduRoleMapper;
import com.code.edu.model.EduRole;
import com.code.edu.service.EduRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduRoleServiceImpl implements EduRoleService {
    @Autowired
    private EduRoleMapper eduRoleMapper;

    @Override
    public EduRole findOne(Long id) {
        return eduRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduRole eduRole) {
        if(eduRole.getId() != null) return update(eduRole);
        return save(eduRole);
    }

    @Override
    public int save(EduRole eduRole) {
        return eduRoleMapper.insertSelective(eduRole);
    }

    @Override
    public int update(EduRole eduRole) {
        return eduRoleMapper.updateByPrimaryKeySelective(eduRole);
    }

    @Override
    public List<EduRole> findAll() {
        return eduRoleMapper.selectAll();
    }
}
