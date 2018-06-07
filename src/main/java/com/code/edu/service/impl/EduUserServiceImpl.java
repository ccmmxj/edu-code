package com.code.edu.service.impl;

import com.code.edu.mapper.EduUserMapper;
import com.code.edu.model.EduUser;
import com.code.edu.service.EduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduUserServiceImpl implements EduUserService {
    @Autowired
    private EduUserMapper eduUserMapper;

    @Override
    public EduUser findOne(Long id) {
        return eduUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduUser eduUser) {
        if(eduUser.getId() != null) return update(eduUser);
        return save(eduUser);
    }

    @Override
    public int save(EduUser eduUser) {
        return eduUserMapper.insertSelective(eduUser);
    }

    @Override
    public int update(EduUser eduUser) {
        return eduUserMapper.updateByPrimaryKeySelective(eduUser);
    }

    @Override
    public List<EduUser> findAll() {
        return eduUserMapper.selectAll();
    }
}
