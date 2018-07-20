package com.code.edu.controller;

import com.code.edu.client.TestClient;
import com.code.edu.dto.UserDto;
import com.code.edu.model.EduCard;
import com.code.edu.utils.LoginUtil;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TestClient testClient;
    @GetMapping("doLogin")
    @ResponseBody
    public Result<UserDto> doLogin() {
        UserDto user = LoginUtil.getLoginUser();
        if(user == null){
            return ResultFactory.newInstaceFailResult("请先登录",403L,null);
        }
        return ResultFactory.newInstaceSuccessResult("登陆成功",200L,user);
    }
    @PostMapping("doLogout")
    @ResponseBody
    public Result<String> doLogout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return ResultFactory.newInstaceSuccessResult("退出成功",200L,null);
        }
        return ResultFactory.newInstaceFailResult("请先登录",403L,null);
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
