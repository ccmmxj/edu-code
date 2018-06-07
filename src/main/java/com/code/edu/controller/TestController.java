package com.code.edu.controller;

import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("manage")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EduCardService eduCardService;
//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("eduLearnAll")
    @ResponseBody
    public List<EduCard> eduLearnAll(){
        return eduCardService.findAll();
    }
    @GetMapping("eduLearnOne")
    @ResponseBody
    public EduCard eduLearnOne(Long id){
        return eduCardService.findOne(id);
    }
}
