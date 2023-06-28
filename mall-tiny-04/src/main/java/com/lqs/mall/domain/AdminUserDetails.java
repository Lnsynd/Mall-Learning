package com.lqs.mall.domain;


import com.lqs.mall.mbg.model.UmsAdmin;
import com.lqs.mall.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录成功后的用户实体
 * Created by 刘千山 on 2023/6/5/005-17:20
 */
public class AdminUserDetails implements UserDetails {

    // 登录的用户
    private UmsAdmin umsAdmin;

    // 权限列表
    private List<UmsPermission> permissionList;

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        返回当前登录用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission-> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
