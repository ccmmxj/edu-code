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
@RequestMapping("manage/card")
public class CardController {
    private final static Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private EduCardService eduCardService;

    @PostMapping("del")
    @ResponseBody
    public Result<EduCard> del(Long id){
        EduCard card = eduCardService.delCard(id,Context.DEFAULT_COMPANY_ID);
        if (card != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, card);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, card);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<EduCard> saveOrUpdate(EduCard eduCard){
        int count = eduCardService.saveOrUpdate(eduCard);
        String message = "保存";
        if(eduCard.getId() != null){
            message = "编辑";
        }
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, eduCard);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, eduCard);
    }
    @PostMapping("list")
    @ResponseBody
    public Result<List<EduCard>> list(){
        return ResultFactory.newInstaceSuccessResult("获取成功", 200L, eduCardService.findCardAll(Context.DEFAULT_COMPANY_ID));
    }

}
