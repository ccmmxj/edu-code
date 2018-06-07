package com.code.edu.controller;

import com.code.edu.client.TestClient;
import com.code.edu.model.EduCard;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TestClient testClient;
    @GetMapping("doLogin")
    @ResponseBody
    public Result<String> doLogin() {
        return ResultFactory.newInstaceSuccessResult("test",200L,"test");
    }
    @GetMapping("manage/eduLearnAllClient")
    @ResponseBody
    public List<EduCard> eduLearnAllClient(){
        //调用远程服务
//      ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://edu/eduLearnAll", List.class);
//        logger.info("res="+ JSON.toJSONString(responseEntity));
//        System.out.println("res="+ JSON.toJSONString(responseEntity));
//        return responseEntity.getBody();
        logger.info("testClient");
        logger.debug("testClient");
        System.out.println("testClient");
        return testClient.eduLearnAll();
    }
}
