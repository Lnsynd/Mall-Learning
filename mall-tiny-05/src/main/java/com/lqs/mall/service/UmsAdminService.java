package com.lqs.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.mall.domain.pojo.UmsAdmin;

/**
* @author Admin
* @description 针对表【ums_admin(后台用户表)】的数据库操作Service
* @createDate 2023-06-07 20:30:43
*/
public interface UmsAdminService extends IService<UmsAdmin> {

    String login(String username,String password);
}
