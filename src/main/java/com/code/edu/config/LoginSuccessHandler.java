package com.code.edu.config;

import com.code.edu.dto.UserDto;
import com.code.edu.utils.LoginUtil;
import com.code.edu.utils.ResultFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 * @author wangruyu
 * @since 2017/3/15-09:57
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDto user = LoginUtil.getLoginUser();
        HttpSession session = request.getSession(false);
        session.setAttribute(user.getEduUser().getUserName(),"ok");
        System.out.println(session.getId());
        LOGGER.info(MessageFormat.format("用户{0}登录成功...",user.getEduUser().getEmpName()));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        user.setSessionId(session.getId());
        String result = objectMapper.writeValueAsString(ResultFactory.newInstaceSuccessResult("登陆成功",200L,user));
        printWriter.print(result);
    }
}
