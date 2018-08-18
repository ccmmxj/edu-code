package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduCard;
import com.code.edu.model.EduAdvert;
import com.code.edu.service.EduAdvertService;
import com.code.edu.service.EduCardService;
import com.code.edu.service.EduAdvertService;
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
@RequestMapping("manage/advert")
public class ManageAdvertController {
    private final static Logger logger = LoggerFactory.getLogger(ManageAdvertController.class);

    @Autowired
    private EduAdvertService eduAdvertService;

    @PostMapping("del")
    @ResponseBody
    public Result<EduAdvert> del(Long id){
        EduAdvert advert = eduAdvertService.delAdvert(id);
        if (advert != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, advert);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, advert);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<EduAdvert> saveOrUpdate(EduAdvert advert){
        String message = "保存";
        if(advert.getId() != null){
            message = "编辑";
        }
        int count = eduAdvertService.saveOrUpdate(advert);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, advert);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, advert);
    }
    //    @PostMapping("list")
//    @ResponseBody
//    public Result<List<EduCard>> list(){
//        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(LoginUtil.getLoginUser().getEduUser().getCompanyId()));
//    }
    @PostMapping("list")
    @ResponseBody
    public TableData<EduAdvert> list(TableData<EduAdvert> tableData,String name){
        return eduAdvertService.findAdvertTable(tableData,name);
    }
    @PostMapping("detail")
    @ResponseBody
    public Result<EduAdvert> detail(Long id){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduAdvertService.findOne(id));
    }

}
