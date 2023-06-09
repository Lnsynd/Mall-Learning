package com.lqs.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.mall.common.utils.JwtTokenUtil;
import com.lqs.mall.domain.dto.AdminUserDetails;
import com.lqs.mall.domain.pojo.UmsAdmin;
import com.lqs.mall.mapper.UmsAdminMapper;
import com.lqs.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 * @description 针对表【ums_admin(后台用户表)】的数据库操作Service实现
 * @createDate 2023-06-07 20:30:43
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {


    public static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        // authenticate用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 如果认证未通过
//        try {
//            if (ObjectUtil.isNull(authenticate)) {
//                System.out.println("验证不通过");
//                throw new UsernameNotFoundException("登录失败");
//            }
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("不通过");
//        }


        // 如果认证通过
        AdminUserDetails adminUserDetails = (AdminUserDetails) authenticate.getPrincipal();
        // 拿到 登录用户
//        UmsAdmin umsAdmin = adminUserDetails.getUmsAdmin();
        // 生成jwt 返回
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtTokenUtil.generateToken(adminUserDetails);
    }
}




