package com.code.edu.controller;

import com.code.edu.model.EduConfig;
import com.code.edu.service.EduConfigService;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("config")
public class BaseConfigController {
    private final static Logger logger = LoggerFactory.getLogger(BaseConfigController.class);

    @Autowired
    private EduConfigService eduConfigService;

    @PostMapping("list/{type}/{companyId}")
    @ResponseBody
    public Result<List<EduConfig>> list(@PathVariable Long companyId, @PathVariable String type){
        return ResultFactory.newInstaceSuccessResult("获取成功",200L,eduConfigService.findConfigByTypeAndComId(companyId,type));
    }

}
