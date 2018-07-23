package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.dto.TableData;
import com.code.edu.dto.WhereDto;
import com.code.edu.mapper.JiajuLunboMapper;
import com.code.edu.model.JiajuLunbo;
import com.code.edu.service.JiajuLunboService;
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
public class JiajuLunboServiceImpl implements JiajuLunboService {
    private final static Logger logger = LoggerFactory.getLogger(JiajuLunboServiceImpl.class);
    @Autowired
    private JiajuLunboMapper jiajuLunboMapper;

    @Override
    public JiajuLunbo delLunbo(Long id, Long companyId) {
        JiajuLunbo lunbo = findOne(id);
        lunbo.setIsDeleted((byte)1);
        lunbo.setCompanyId(companyId);
        logger.info("delLunbo after =====> {}", JSON.toJSONString(lunbo));
        int count = jiajuLunboMapper.updateByPrimaryKeySelective(lunbo);
        logger.info("delLunbo before =====> {}", JSON.toJSONString(lunbo));
        if(count>0) {
            return lunbo;
        }
        return null;
    }

    @Override
    public List<JiajuLunbo> findLunboAll(Long companyId) {
        Example example = Example.builder(JiajuLunbo.class).where(WhereDto.defaultWhere(companyId)).build();
        logger.info("findLunboAll =====> companyId:{}", companyId);
        return jiajuLunboMapper.selectByExample(example);
    }

    @Override
    public JiajuLunbo findOne(Long id, Long companyId) {
        Example example = Example.builder(JiajuLunbo.class).where(WhereDto.defaultWhere(companyId).andEqualTo("id",id)).build();
        logger.info("findOne =====> id:{},companyId:{}", id,companyId);
        return jiajuLunboMapper.selectOneByExample(example);
    }

    @Override
    public TableData<JiajuLunbo> findLunboTable(TableData<JiajuLunbo> tableData, Long companyId) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.defaultWhere(companyId);
        Example.Builder builder = Example.builder(JiajuLunbo.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
//        List<EduCard> jiajuLunbos = findCardAll(companyId);
        List<JiajuLunbo> jiajuLunbos = jiajuLunboMapper.selectByExample(example);
        PageInfo<JiajuLunbo> page= new PageInfo<>(jiajuLunbos);
//        tableData.setTotal((int)page.getTotal());
//        tableData.setRows(jiajuLunbos);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,jiajuLunbos);
    }

    @Override
    public List<JiajuLunbo> findLunboByTypeAndComId(Long companyId, Byte type) {
        Example example = Example.builder(JiajuLunbo.class).where(WhereDto.defaultWhere(companyId).andEqualTo("type",type)).build();
        List<JiajuLunbo> jiajuLunbos = jiajuLunboMapper.selectByExample(example);
        return jiajuLunbos;
    }

    @Override
    public JiajuLunbo findOne(Long id) {
        return jiajuLunboMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(JiajuLunbo jiajuLunbo) {
        if(jiajuLunbo.getId() != null) return update(jiajuLunbo);
        return save(jiajuLunbo);
    }

    @Override
    public int save(JiajuLunbo jiajuLunbo) {
        Date now = new Date();
        jiajuLunbo.setGmtModified(now);
        jiajuLunbo.setGmtCreated(now);
        return jiajuLunboMapper.insertSelective(jiajuLunbo);
    }

    @Override
    public int update(JiajuLunbo jiajuLunbo) {
        Date now = new Date();
        jiajuLunbo.setGmtModified(now);
        return jiajuLunboMapper.updateByPrimaryKeySelective(jiajuLunbo);
    }

    @Override
    public List<JiajuLunbo> findAll() {
        return jiajuLunboMapper.selectAll();
    }
}
