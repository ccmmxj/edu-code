package com.code.edu.controller;

import com.code.edu.model.EduAdvert;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduAdvertService;
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
@RequestMapping("advert")
public class AdvertController {
    private final static Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Autowired
    private EduAdvertService eduAdvertService;

    @PostMapping("list")
    @ResponseBody
    public Result<List<EduAdvert>> list(Byte type){
        return ResultFactory.newInstaceSuccessResult("获取成功",200L,eduAdvertService.findByType(type));
    }

}
