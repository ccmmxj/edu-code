package com.code.edu.dto;

import com.code.edu.model.EduPersiom;
import com.code.edu.model.EduRole;
import com.code.edu.model.EduUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDto implements UserDetails {
    private String sessionId;
    private EduUser eduUser;
    private List<EduRole> eduRoles;
    private List<EduMenuDto> eduMenuList;
    private List<EduPersiom> eduPersioms;

    public List<EduRole> getEduRoles() {
        return eduRoles;
    }

    public void setEduRoles(List<EduRole> eduRoles) {
        this.eduRoles = eduRoles;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
        if(eduPersioms != null){
            List<GrantedAuthority> list = new ArrayList<>();
            eduPersioms.forEach((value)->{
                GrantedAuthority au = new SimpleGrantedAuthority(value.getName());
                list.add(au);
            });
            return list;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return eduUser.getPassword();
    }

    @Override
    public String getUsername() {
        return eduUser.getUserName();
    }

    /*
        *帐号是否不过期，false则验证不通过
        */
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * 帐号是否不锁定，false则验证不通过
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * 凭证是否不过期，false则验证不通过
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * 该帐号是否启用，false则验证不通过
     */
    public boolean isEnabled() {
        return !Boolean.valueOf(eduUser.getIsDeleted()+"");
    }

    public EduUser getEduUser() {
        return eduUser;
    }

    public void setEduUser(EduUser eduUser) {
        this.eduUser = eduUser;
    }

    public List<EduMenuDto> getEduMenuList() {
        return eduMenuList;
    }

    public void setEduMenuList(List<EduMenuDto> eduMenuList) {
        this.eduMenuList = eduMenuList;
    }

    public List<EduPersiom> getEduPersioms() {
        return eduPersioms;
    }

    public void setEduPersioms(List<EduPersiom> eduPersioms) {
        this.eduPersioms = eduPersioms;
    }
}
