package com.code.edu.utils;

import com.code.edu.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wangruyu
 * @since 2017/3/15-09:58
 */
public class LoginUtil {
    private static SecurityContext context = SecurityContextHolder.getContext();
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUtil.class);
    public static UserDto getLoginUser() {
        LOGGER.info(Thread.currentThread().getName());
        if (context == null) {
            return null;
        }
        if (context.getAuthentication() == null || context.getAuthentication().getPrincipal() == null) {
            return null;
        }
        if (context.getAuthentication().getPrincipal() instanceof UserDetails) {
            return (UserDto) context.getAuthentication().getPrincipal();
        }
        return null;
    }
}
