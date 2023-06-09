package com.lqs.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqs.mall.domain.pojo.UmsAdmin;
import com.lqs.mall.domain.dto.AdminUserDetails;
import com.lqs.mall.mapper.UmsAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by 刘千山 on 2023/6/9/009-17:35
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 数据库中查询用户是否存在
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername, username);
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(wrapper);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //TODO 分配用户的权限信息
        return new AdminUserDetails(umsAdmin, null);
    }
}
