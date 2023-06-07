package com.lqs.mall.dao;

import com.lqs.mall.mbg.model.UmsPermission;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * Created by 刘千山 on 2023/6/5/005-20:31
 */
public interface UmsAdminRoleRelationDao {

    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
