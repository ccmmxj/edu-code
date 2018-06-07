package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.mapper.EduCardMapper;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

@Service
public class EduCardServiceImpl implements EduCardService {
    private final static Logger logger = LoggerFactory.getLogger(EduCardServiceImpl.class);
    @Autowired
    private EduCardMapper eduCardMapper;

    @Override
    public EduCard findOne(Long id) {
        return eduCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduCard eduCard) {
        if(eduCard.getId() != null) return update(eduCard);
        return save(eduCard);
    }

    @Override
    public int save(EduCard eduCard) {
        return eduCardMapper.insertSelective(eduCard);
    }

    @Override
    public int update(EduCard eduCard) {
        return eduCardMapper.updateByPrimaryKeySelective(eduCard);
    }

    @Override
    public List<EduCard> findAll() {
        return eduCardMapper.selectAll();
    }

    @Override
    @Transactional
    public EduCard delCard(Long id,Long companyId) {
        EduCard card = findOne(id);
        card.setIsDeleted((byte)1);
        card.setCompanyId(companyId);
        logger.info("delCard after =====> {}", JSON.toJSONString(card));
        int count = eduCardMapper.updateByPrimaryKeySelective(card);
        logger.info("delCard before =====> {}", JSON.toJSONString(card));
        if(count>0) {
            return card;
        }
        return null;
    }

    @Override
    public List<EduCard> findCardAll(Long companyId) {
        Example example = Example.builder(EduCard.class).where(Sqls.custom().andEqualTo("companyId",companyId).andEqualTo("isDeleted",0)).build();
        logger.info("findCardAll =====> companyId:{}", companyId);
        return eduCardMapper.selectByExample(example);
    }
}
