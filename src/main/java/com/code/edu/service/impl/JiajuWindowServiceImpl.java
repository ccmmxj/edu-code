package com.code.edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.edu.dto.TableData;
import com.code.edu.dto.WhereDto;
import com.code.edu.mapper.JiajuWindowMapper;
import com.code.edu.model.JiajuWindow;
import com.code.edu.service.JiajuWindowService;
import com.code.edu.service.JiajuWindowService;
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
public class JiajuWindowServiceImpl implements JiajuWindowService {
    private final static Logger logger = LoggerFactory.getLogger(JiajuWindowServiceImpl.class);
    @Autowired
    private JiajuWindowMapper jiajuWindowMapper;

    @Override
    public JiajuWindow delWindow(Long id, Long companyId) {
        JiajuWindow window = findOne(id);
        window.setIsDeleted((byte)1);
        window.setCompanyId(companyId);
        logger.info("delWindow after =====> {}", JSON.toJSONString(window));
        int count = jiajuWindowMapper.updateByPrimaryKeySelective(window);
        logger.info("delWindow before =====> {}", JSON.toJSONString(window));
        if(count>0) {
            return window;
        }
        return null;
    }

    @Override
    public List<JiajuWindow> findWindowAll(Long companyId) {
        Example example = Example.builder(JiajuWindow.class).where(WhereDto.defaultWhere(companyId)).build();
        logger.info("findWindowAll =====> companyId:{}", companyId);
        return jiajuWindowMapper.selectByExample(example);
    }

    @Override
    public JiajuWindow findOne(Long id, Long companyId) {
        Example example = Example.builder(JiajuWindow.class).where(WhereDto.defaultWhere(companyId).andEqualTo("id",id)).build();
        logger.info("findOne =====> id:{},companyId:{}", id,companyId);
        return jiajuWindowMapper.selectOneByExample(example);
    }

    @Override
    public TableData<JiajuWindow> findWindowTable(TableData<JiajuWindow> tableData, Long companyId,String title) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.defaultWhere(companyId);
        if(StringUtils.isNotBlank(title)){
            sqls.andLike("title","%"+title+"%");
        }
        Example.Builder builder = Example.builder(JiajuWindow.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
//        List<EduCard> jiajuWindows = findCardAll(companyId);
        List<JiajuWindow> jiajuWindows = jiajuWindowMapper.selectByExample(example);
        PageInfo<JiajuWindow> page= new PageInfo<>(jiajuWindows);
//        tableData.setTotal((int)page.getTotal());
//        tableData.setRows(jiajuWindows);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,jiajuWindows);
    }

    @Override
    public List<JiajuWindow> findWindowByTypeAndComId(Long companyId, Byte type) {
        Example example = Example.builder(JiajuWindow.class).where(WhereDto.defaultWhere(companyId).andEqualTo("type",type)).build();
        List<JiajuWindow> jiajuWindows = jiajuWindowMapper.selectByExample(example);
        return jiajuWindows;
    }

    @Override
    public JiajuWindow findOne(Long id) {
        return jiajuWindowMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(JiajuWindow jiajuWindow) {
        if(jiajuWindow.getId() != null) return update(jiajuWindow);
        return save(jiajuWindow);
    }

    @Override
    public int save(JiajuWindow jiajuWindow) {
        Date now = new Date();
        jiajuWindow.setGmtModified(now);
        jiajuWindow.setGmtCreated(now);
        return jiajuWindowMapper.insertSelective(jiajuWindow);
    }

    @Override
    public int update(JiajuWindow jiajuWindow) {
        Date now = new Date();
        jiajuWindow.setGmtModified(now);
        return jiajuWindowMapper.updateByPrimaryKeySelective(jiajuWindow);
    }

    @Override
    public List<JiajuWindow> findAll() {
        return jiajuWindowMapper.selectAll();
    }
}
