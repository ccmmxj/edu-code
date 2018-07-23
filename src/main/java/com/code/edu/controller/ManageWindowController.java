package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.model.JiajuWindow;
import com.code.edu.service.JiajuWindowService;
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
@RequestMapping("manage/window")
public class ManageWindowController {
    private final static Logger logger = LoggerFactory.getLogger(ManageWindowController.class);

    @Autowired
    private JiajuWindowService jiajuWindowService;

    @PostMapping("del")
    @ResponseBody
    public Result<JiajuWindow> del(Long id){
        JiajuWindow jiajuWindow = jiajuWindowService.delWindow(id,LoginUtil.getLoginUser().getEduUser().getCompanyId());
        if (jiajuWindow != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, jiajuWindow);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, jiajuWindow);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<JiajuWindow> saveOrUpdate(JiajuWindow jiajuWindow){
        String message = "保存";
        if(jiajuWindow.getId() != null){
            message = "编辑";
        }
        jiajuWindow.setCompanyId(LoginUtil.getLoginUser().getEduUser().getCompanyId());
        int count = jiajuWindowService.saveOrUpdate(jiajuWindow);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, jiajuWindow);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, jiajuWindow);
    }
//    @PostMapping("list")
//    @ResponseBody
//    public Result<List<EduCard>> list(){
//        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(LoginUtil.getLoginUser().getEduUser().getCompanyId()));
//    }
    @PostMapping("list")
    @ResponseBody
    public TableData<JiajuWindow> list(TableData<JiajuWindow> tableData,String title){
        return jiajuWindowService.findWindowTable(tableData,LoginUtil.getLoginUser().getEduUser().getCompanyId(),title);
    }
    @PostMapping("detail")
    @ResponseBody
    public Result<JiajuWindow> detail(Long id){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, jiajuWindowService.findOne(id,LoginUtil.getLoginUser().getEduUser().getCompanyId()));
    }

}
