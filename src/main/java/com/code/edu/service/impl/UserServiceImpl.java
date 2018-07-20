package com.code.edu.service.impl;

import com.code.edu.dto.EduMenuDto;
import com.code.edu.dto.UserDto;
import com.code.edu.mapper.EduMenuMapper;
import com.code.edu.mapper.EduPersiomMapper;
import com.code.edu.mapper.EduRoleMapper;
import com.code.edu.mapper.EduUserMapper;
import com.code.edu.model.EduMenu;
import com.code.edu.model.EduPersiom;
import com.code.edu.model.EduRole;
import com.code.edu.model.EduUser;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private EduPersiomMapper eduPersiomMapper;
    @Autowired
    private EduRoleMapper eduRoleMapper;
    @Autowired
    private EduMenuMapper eduMenuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = new UserDto();
        EduUser user = eduUserMapper.selectByUsername(username);
        logger.info("username:==================={}============",username);
        if (user != null) {
            List<EduPersiom> permissions = eduPersiomMapper.selectByUserId(user.getId());
            List<EduRole> roles = eduRoleMapper.selectByUserId(user.getId());
            List<EduMenuDto> menus = eduMenuMapper.selectByUserId(user.getId());
            menus.forEach((value)->{
                value.setChildEduMenu(eduMenuMapper.selectByPid(value.getId()));
            });
            userDto.setEduUser(user);
            userDto.setEduPersioms(permissions);
            userDto.setEduMenuList(menus);
            userDto.setEduRoles(roles);
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (EduPersiom permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
//
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            }
//            return User.withUsername(user.getUserName()).password(user.getPassword())
//                    .roles(roles.stream().map( (value)-> value.getName()).collect(Collectors.toList()).toArray(new String[0]))
//                    .authorities(permissions.stream().map(value -> value.getName()).collect(Collectors.toList()).toArray(new String[0])).build();
            return userDto;
        } else {
            throw new UsernameNotFoundException("user: " + username + " do not exist!");
        }
    }
}
