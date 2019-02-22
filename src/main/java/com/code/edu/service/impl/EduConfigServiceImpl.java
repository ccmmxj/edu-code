package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.dto.TableData;
import com.code.edu.dto.WhereDto;
import com.code.edu.mapper.EduConfigMapper;
import com.code.edu.model.EduConfig;
import com.code.edu.service.EduConfigService;
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
public class EduConfigServiceImpl implements EduConfigService {
    private final static Logger logger = LoggerFactory.getLogger(EduConfigServiceImpl.class);
    @Autowired
    private EduConfigMapper eduConfigMapper;

    @Override
    public EduConfig findOne(Long id) {
        return eduConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(EduConfig eduConfig) {
        if(eduConfig.getId() != null) return update(eduConfig);
        return save(eduConfig);
    }

    @Override
    public int save(EduConfig eduConfig) {
        Date now = new Date();
        eduConfig.setGmtModified(now);
        eduConfig.setGmtCreated(now);
        return eduConfigMapper.insert(eduConfig);
    }

    @Override
    public int update(EduConfig eduConfig) {
        Date now = new Date();
        eduConfig.setGmtModified(now);
        return eduConfigMapper.updateByPrimaryKeySelective(eduConfig);
    }

    @Override
    public List<EduConfig> findAll() {
        return eduConfigMapper.selectAll();
    }

    @Override
    public EduConfig delConfig(Long id, Long companyId) {
        EduConfig config = findOne(id);
        if(config == null) return null;
        Example example = Example.builder(EduConfig.class).where(WhereDto.notInIsDeletedWhere(companyId).andEqualTo("id",id)).build();

        logger.info("delConfig =====> {}", JSON.toJSONString(config));
        int count = eduConfigMapper.deleteByExample(example);
        if(count>0) {
            return config;
        }
        return null;
    }
    @Override
    public TableData<EduConfig> findConfigTable(TableData<EduConfig> tableData, Long companyId, String title) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.notInIsDeletedWhere(companyId);
        if(StringUtils.isNotBlank(title)){
            sqls.andLike("title","%"+title+"%");
        }
        Example.Builder builder = Example.builder(EduConfig.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
//        List<EduConfig> cards = findCardAll(companyId);
        List<EduConfig> cards = eduConfigMapper.selectByExample(example);
        PageInfo<EduConfig> page= new PageInfo<>(cards);
//        tableData.setTotal((int)page.getTotal());
//        tableData.setRows(cards);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,cards);
    }

    @Override
    public EduConfig findOne(Long id, Long companyId) {
        Example example = Example.builder(EduConfig.class).where(WhereDto.notInIsDeletedWhere(companyId).andEqualTo("id",id)).build();
        logger.info("findOne =====> id:{},companyId:{}", id,companyId);
        return eduConfigMapper.selectOneByExample(example);
    }
}
