package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.dto.TableData;
import com.code.edu.dto.WhereDto;
import com.code.edu.mapper.EduCardMapper;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import com.code.edu.utils.TableDataFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Date;
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
        Date now = new Date();
        eduCard.setGmtModified(now);
        eduCard.setGmtCreated(now);
        return eduCardMapper.insertSelective(eduCard);
    }

    @Override
    public int update(EduCard eduCard) {
        Date now = new Date();
        eduCard.setGmtModified(now);
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
        int count = update(card);
        logger.info("delCard before =====> {}", JSON.toJSONString(card));
        if(count>0) {
            return card;
        }
        return null;
    }

    @Override
    public List<EduCard> findCardAll(Long companyId) {
        Example example = Example.builder(EduCard.class).where(WhereDto.defaultWhere(companyId)).build();
        logger.info("findCardAll =====> companyId:{}", companyId);
        return eduCardMapper.selectByExample(example);
    }

    @Override
    public List<EduCard> findCardByTypeAndComId(Long companyId, Byte type) {
        Example example = Example.builder(EduCard.class).where(WhereDto.defaultWhere(companyId).andEqualTo("type",type)).build();
        List<EduCard> cards = eduCardMapper.selectByExample(example);
        return cards;
    }

    @Override
    public TableData<EduCard> findCardTable(TableData<EduCard> tableData, Long companyId,String title) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.defaultWhere(companyId);
        if(StringUtils.isNotBlank(title)){
            sqls.andLike("title","%"+title+"%");
        }
        Example.Builder builder = Example.builder(EduCard.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
//        List<EduCard> cards = findCardAll(companyId);
        List<EduCard> cards = eduCardMapper.selectByExample(example);
        PageInfo<EduCard> page= new PageInfo<>(cards);
//        tableData.setTotal((int)page.getTotal());
//        tableData.setRows(cards);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,cards);
    }

    @Override
    public EduCard findOne(Long id, Long companyId) {
        Example example = Example.builder(EduCard.class).where(WhereDto.defaultWhere(companyId).andEqualTo("id",id)).build();
        logger.info("findOne =====> id:{},companyId:{}", id,companyId);
        return eduCardMapper.selectOneByExample(example);
    }

    @Override
    public List<EduCard> findCardByComId(Long companyId) {
        Example example = Example.builder(EduCard.class).where(WhereDto.defaultWhere(companyId)).build();
        List<EduCard> cards = eduCardMapper.selectByExample(example);
        return cards;
    }
}
