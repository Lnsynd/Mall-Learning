package com.lqs.mall.domain.dto;

import com.lqs.mall.domain.pojo.UmsAdmin;
import com.lqs.mall.domain.pojo.UmsPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by 刘千山 on 2023/6/9/009-17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;

    private List<UmsPermission> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }
}
