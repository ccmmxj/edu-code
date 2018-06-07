package com.code.edu.config;

import com.code.edu.utils.ResultFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangruyu
 * @since 2017/3/15-10:10
 */
public class LoginFailedHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginFailedHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        if (exception instanceof InternalAuthenticationServiceException) {
            printWriter.print(objectMapper.writeValueAsString(ResultFactory.newInstaceFailResult(exception.getMessage(),401L,  exception)));
        } else if (exception instanceof UsernameNotFoundException || exception instanceof AuthenticationException) {
            printWriter.print(objectMapper.writeValueAsString(ResultFactory.newInstaceFailResult("用户名或者密码不正确",401L,  exception)));
        } else {
            printWriter.print(objectMapper.writeValueAsString(ResultFactory.newInstaceFailResult("网络错误",402L,  exception)));
        }
        printWriter.close();
    }
}
