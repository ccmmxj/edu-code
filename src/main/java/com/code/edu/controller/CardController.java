package com.code.edu.controller;

import com.code.edu.dto.TableData;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
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
@RequestMapping("card")
public class CardController {
    private final static Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private EduCardService eduCardService;

    @PostMapping("list/{companyId}")
    @ResponseBody
    public Result<List<EduCard>> list(@PathVariable Long companyId, Byte type){
        return ResultFactory.newInstaceSuccessResult("获取成功",200L,eduCardService.findCardByTypeAndComId(companyId,type));
    }

    @PostMapping("detail/{companyId}/{id}")
    @ResponseBody
    public Result<EduCard> detail( @PathVariable Long id,@PathVariable Long companyId){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L,eduCardService.findOne(id,companyId));
    }

}
