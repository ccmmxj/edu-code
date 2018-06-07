package com.code.edu.service.impl;

import com.code.edu.mapper.EduPersiomMapper;
import com.code.edu.model.EduPersiom;
import com.code.edu.service.EduPersiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduPersiomServiceImpl implements EduPersiomService {
    @Autowired
    private EduPersiomMapper eduPersiomMapper;

    @Override
    public EduPersiom findOne(Long id) {
        return eduPersiomMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduPersiom eduPersiom) {
        if(eduPersiom.getId() != null) return update(eduPersiom);
        return save(eduPersiom);
    }

    @Override
    public int save(EduPersiom eduPersiom) {
        return eduPersiomMapper.insertSelective(eduPersiom);
    }

    @Override
    public int update(EduPersiom eduPersiom) {
        return eduPersiomMapper.updateByPrimaryKeySelective(eduPersiom);
    }

    @Override
    public List<EduPersiom> findAll() {
        return eduPersiomMapper.selectAll();
    }
}
