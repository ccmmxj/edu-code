package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.enums.ConfigTypeEnum;
import com.code.edu.model.EduConfig;
import com.code.edu.model.EduConfig;
import com.code.edu.service.EduConfigService;
import com.code.edu.service.EduConfigService;
import com.code.edu.utils.LoginUtil;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("manage/config")
public class ManageBaseConfigController {
    private final static Logger logger = LoggerFactory.getLogger(ManageBaseConfigController.class);

    @Autowired
    private EduConfigService eduConfigService;

    @PostMapping("del")
    @ResponseBody
    public Result<EduConfig> del(Long id){
        EduConfig config = eduConfigService.delConfig(id,LoginUtil.getLoginUser().getEduUser().getCompanyId());
        if (config != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, config);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, config);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<EduConfig> saveOrUpdate(EduConfig eduConfig){
        String message = "保存";
        if(eduConfig.getId() != null){
            message = "编辑";
        }
        eduConfig.setCompanyId(LoginUtil.getLoginUser().getEduUser().getCompanyId());
        int count = eduConfigService.saveOrUpdate(eduConfig);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, eduConfig);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, eduConfig);
    }
//    @PostMapping("list")
//    @ResponseBody
//    public Result<List<EduConfig>> list(){
//        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduConfigService.findConfigAll(LoginUtil.getLoginUser().getEduUser().getCompanyId()));
//    }
    @PostMapping("card/list")
    @ResponseBody
    public TableData<EduConfig> cardList(TableData<EduConfig> tableData, String title){
        return eduConfigService.findConfigTable(tableData,LoginUtil.getLoginUser().getEduUser().getCompanyId(),title,ConfigTypeEnum.CARD_TYPE);
    }
    @PostMapping("detail")
    @ResponseBody
    public Result<EduConfig> detail(Long id){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduConfigService.findOne(id,LoginUtil.getLoginUser().getEduUser().getCompanyId()));
    }

}
