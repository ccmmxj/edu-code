package com.code.edu.controller;

import com.code.edu.common.Context;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("card")
public class CardController {
    private final static Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private EduCardService eduCardService;

    @PostMapping("list")
    @ResponseBody
    public Result<List<EduCard>> list(){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(Context.DEFAULT_COMPANY_ID));
    }

}
