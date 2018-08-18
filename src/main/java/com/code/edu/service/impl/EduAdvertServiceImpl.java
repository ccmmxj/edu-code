package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.dto.TableData;
import com.code.edu.dto.WhereDto;
import com.code.edu.mapper.EduAdvertMapper;
import com.code.edu.model.EduAdvert;
import com.code.edu.service.EduAdvertService;
import com.code.edu.service.EduAdvertService;
import com.code.edu.utils.TableDataFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Date;
import java.util.List;

@Service
public class EduAdvertServiceImpl implements EduAdvertService {
    private final static Logger logger = LoggerFactory.getLogger(EduAdvertServiceImpl.class);
    @Autowired
    private EduAdvertMapper eduAdvertMapper;

    @Override
    public EduAdvert delAdvert(Long id) {
        EduAdvert advert = findOne(id);
        advert.setIsDeleted((byte)1);
        logger.info("delAdvert after =====> {}", JSON.toJSONString(advert));
        int count = update(advert);
        logger.info("delAdvert before =====> {}", JSON.toJSONString(advert));
        if(count>0) {
            return advert;
        }
        return null;
    }

    @Override
    public List<EduAdvert> findAdvertAll() {
        Example example = Example.builder(EduAdvert.class).where(WhereDto.notInCompanyIdWhere()).build();
        logger.info("findAdvertAll =====> {}");
        return eduAdvertMapper.selectByExample(example);
    }

    @Override
    public List<EduAdvert> findByType(Byte type) {
        Example example = Example.builder(EduAdvert.class).where(WhereDto.notInCompanyIdWhere().andEqualTo("type",type)).orderByDesc("gmtModified").build();
        return eduAdvertMapper.selectByExample(example);
    }

    @Override
    public EduAdvert findOne(Long id) {
        Example example = Example.builder(EduAdvert.class).where(WhereDto.notInCompanyIdWhere().andEqualTo("id",id)).build();
        logger.info("findOne =====> id:{}", id);
        return eduAdvertMapper.selectOneByExample(example);
    }

    @Override
    public TableData<EduAdvert> findAdvertTable(TableData<EduAdvert> tableData,String name) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.notInCompanyIdWhere();
        if(StringUtils.isNotBlank(name)){
            sqls.andLike("name","%"+name+"%");
        }
        Example.Builder builder = Example.builder(EduAdvert.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
        List<EduAdvert> adverts = eduAdvertMapper.selectByExample(example);
        PageInfo<EduAdvert> page= new PageInfo<>(adverts);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,adverts);
    }

    @Override
    public int saveOrUpdate(EduAdvert jiajuAdvert) {
        if(jiajuAdvert.getId() != null) return update(jiajuAdvert);
        return save(jiajuAdvert);
    }

    @Override
    public int save(EduAdvert jiajuAdvert) {
        Date now = new Date();
        jiajuAdvert.setGmtModified(now);
        jiajuAdvert.setGmtCreated(now);
        return eduAdvertMapper.insertSelective(jiajuAdvert);
    }

    @Override
    public int update(EduAdvert jiajuAdvert) {
        Date now = new Date();
        jiajuAdvert.setGmtModified(now);
        return eduAdvertMapper.updateByPrimaryKeySelective(jiajuAdvert);
    }

    @Override
    public List<EduAdvert> findAll() {
        return eduAdvertMapper.selectAll();
    }
}
