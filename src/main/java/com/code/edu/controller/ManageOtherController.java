package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.model.Resource;
import com.code.edu.service.EduOtherService;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("manage/other")
public class ManageOtherController {

    @Autowired
    private EduOtherService eduOtherService;

    @GetMapping("clearResource")
    @ResponseBody
    public Result<Integer> clearResource(){
        return ResultFactory.newInstaceSuccessResult("清除成功", 200L, eduOtherService.clearResource());
    }

    @PostMapping("resourceList")
    @ResponseBody
    public TableData<Resource> resourceList(TableData<Resource> tableData, String title){
        return eduOtherService.findDelResourceTable(tableData, title);
    }

    @PostMapping("restoreResource")
    @ResponseBody
    public Result<String> restoreResource(String url){
        return ResultFactory.newInstaceSuccessResult("恢复成功", 200L, eduOtherService.restoreResource(url));
    }
}
