package com.lqs.mall.service;

import com.lqs.mall.domain.AdminUserDetails;
import com.lqs.mall.domain.UmsResource;

import java.util.List;

public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
