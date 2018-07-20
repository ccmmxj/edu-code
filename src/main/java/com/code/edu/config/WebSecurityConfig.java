package com.code.edu.config;

import com.code.edu.mapper.EduPersiomMapper;
import com.code.edu.mapper.EduSecurityMapper;
import com.code.edu.model.EduPersiom;
import com.code.edu.model.EduSecurity;
import com.code.edu.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EduSecurityMapper eduSecurityMapper;
    @Autowired
    private EduPersiomMapper eduPersiomMapper;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService); //user Details Service验证
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SecurityContextHolder.getContext().setAuthentication(null);
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        LoginFailedHandler loginFailedHandler = new LoginFailedHandler();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        List<EduSecurity> eduSecuritys = eduSecurityMapper.selectAll();
        eduSecuritys.stream().forEach((value) -> {
            List<EduPersiom> eduPersioms = eduPersiomMapper.selectBySecurityId(value.getId());
            try {
                http.authorizeRequests().antMatchers(value.getUrl()).hasAnyAuthority(eduPersioms.stream().map(eduPersiom -> eduPersiom.getName()).collect(Collectors.toList()).toArray(new String[0]));
            } catch (Exception e) {
                logger.info("configure ---- {}",e.getMessage());
                e.printStackTrace();
            }
        });
        http.authorizeRequests()
//                .antMatchers("/manage/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailedHandler)
                .and().logout().deleteCookies("JSESSIONID").permitAll()
                .and().csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and().logout().permitAll()
//                .and().csrf().disable();
    }
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        http.csrf().disable();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}
