//package com.code.edu.config;
//
//import com.code.edu.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//
////@Configuration
////@EnableWebSecurity // 注解开启Spring Security的功能
//public class CloudSecurityConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private UserServiceImpl userService;
//
////    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService); //user Details Service验证
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
//        clients.inMemory() // 使用in-memory存储
//                .withClient("edu") // client_id
//                .secret("secret") // client_secret
//                .authorizedGrantTypes("authorization_code") // 该client允许的授权类型
//                .scopes("app"); // 允许的授权范围
//    }
//    //    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
////        //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
////    }
//}
