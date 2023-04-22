package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.manage.EduCardManage;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import com.code.edu.utils.LoginUtil;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("manage/card")
public class ManageCardController {
    private final static Logger logger = LoggerFactory.getLogger(ManageCardController.class);

    @Autowired
    private EduCardService eduCardService;

    @Autowired
    private EduCardManage eduCardManage;

    @PostMapping("del")
    @ResponseBody
    public Result<EduCard> del(Long id){
        EduCard card = eduCardService.delCard(id,LoginUtil.getLoginUser().getEduUser().getCompanyId());
        if (card != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, card);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, card);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<EduCard> saveOrUpdate(EduCard eduCard){
        String message = "保存";
        if(eduCard.getId() != null){
            message = "编辑";
        }
        eduCard.setCompanyId(LoginUtil.getLoginUser().getEduUser().getCompanyId());
        int count = eduCardService.saveOrUpdate(eduCard);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, eduCard);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, eduCard);
    }
//    @PostMapping("list")
//    @ResponseBody
//    public Result<List<EduCard>> list(){
//        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(LoginUtil.getLoginUser().getEduUser().getCompanyId()));
//    }
    @PostMapping("list")
    @ResponseBody
    public TableData<EduCard> list(TableData<EduCard> tableData, String title){
        return eduCardService.findCardTable(tableData,LoginUtil.getLoginUser().getEduUser().getCompanyId(),title);
    }
    @PostMapping("detail")
    @ResponseBody
    public Result<EduCard> detail(Long id){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findOne(id,LoginUtil.getLoginUser().getEduUser().getCompanyId()));
    }

    @GetMapping("clearResource")
    @ResponseBody
    public Result<Integer> clearResource(){
        return ResultFactory.newInstaceSuccessResult("清除成功", 200L, eduCardManage.clearResource());
    }

}
