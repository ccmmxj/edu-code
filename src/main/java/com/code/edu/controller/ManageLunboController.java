package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduCard;
import com.code.edu.model.JiajuLunbo;
import com.code.edu.service.EduCardService;
import com.code.edu.service.JiajuLunboService;
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
@RequestMapping("manage/lunbo")
public class ManageLunboController {
    private final static Logger logger = LoggerFactory.getLogger(ManageLunboController.class);

    @Autowired
    private JiajuLunboService jiajuLunboService;

    @PostMapping("del")
    @ResponseBody
    public Result<JiajuLunbo> del(Long id){
        JiajuLunbo jiajuLunbo = jiajuLunboService.delLunbo(id,LoginUtil.getLoginUser().getEduUser().getCompanyId());
        if (jiajuLunbo != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, jiajuLunbo);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, jiajuLunbo);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<JiajuLunbo> saveOrUpdate(JiajuLunbo jiajuLunbo){
        String message = "保存";
        if(jiajuLunbo.getId() != null){
            message = "编辑";
        }
        jiajuLunbo.setCompanyId(LoginUtil.getLoginUser().getEduUser().getCompanyId());
        int count = jiajuLunboService.saveOrUpdate(jiajuLunbo);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, jiajuLunbo);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, jiajuLunbo);
    }
//    @PostMapping("list")
//    @ResponseBody
//    public Result<List<EduCard>> list(){
//        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(LoginUtil.getLoginUser().getEduUser().getCompanyId()));
//    }
    @PostMapping("list")
    @ResponseBody
    public TableData<JiajuLunbo> list(TableData<JiajuLunbo> tableData){
        return jiajuLunboService.findLunboTable(tableData,LoginUtil.getLoginUser().getEduUser().getCompanyId());
    }
    @PostMapping("detail")
    @ResponseBody
    public Result<JiajuLunbo> detail(Long id){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, jiajuLunboService.findOne(id,LoginUtil.getLoginUser().getEduUser().getCompanyId()));
    }

}
