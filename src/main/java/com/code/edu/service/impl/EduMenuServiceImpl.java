package com.code.edu.service.impl;

import com.code.edu.mapper.EduMenuMapper;
import com.code.edu.model.EduMenu;
import com.code.edu.service.EduMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduMenuServiceImpl implements EduMenuService {
    @Autowired
    private EduMenuMapper eduMenuMapper;

    @Override
    public EduMenu findOne(Long id) {
        return eduMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduMenu eduMenu) {
        if(eduMenu.getId() != null) return update(eduMenu);
        return save(eduMenu);
    }

    @Override
    public int save(EduMenu eduMenu) {
        return eduMenuMapper.insertSelective(eduMenu);
    }

    @Override
    public int update(EduMenu eduMenu) {
        return eduMenuMapper.updateByPrimaryKeySelective(eduMenu);
    }

    @Override
    public List<EduMenu> findAll() {
        return eduMenuMapper.selectAll();
    }
}
