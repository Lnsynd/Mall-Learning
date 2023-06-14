package com.lqs.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.common.utils.JwtTokenUtil;
import com.lqs.mall.dao.UmsAdminRoleRelationDao;
import com.lqs.mall.domain.dto.AdminUserDetails;
import com.lqs.mall.domain.pojo.UmsAdmin;
import com.lqs.mall.domain.pojo.UmsPermission;
import com.lqs.mall.mapper.UmsAdminMapper;
import com.lqs.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Admin
 * @description 针对表【ums_admin(后台用户表)】的数据库操作Service实现
 * @createDate 2023-06-07 20:30:43
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Override
    public String login(String username, String password) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;

        // authenticate用户认证
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 如果认证未通过
//        if (ObjectUtil.isNull(authenticate)) {
//            throw new UsernameNotFoundException("登录失败");
//        }
//
//        // 如果认证通过
//        AdminUserDetails adminUserDetails = (AdminUserDetails) authenticate.getPrincipal();
//        LOGGER.info("用户信息为:", adminUserDetails.getUmsAdmin().toString());
//        // 拿到 登录用户
////        UmsAdmin umsAdmin = adminUserDetails.getUmsAdmin();
//        // 生成jwt 返回
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        return jwtTokenUtil.generateToken(adminUserDetails);
    }

    /**
     * 退出登录
     */
    @Override
    public CommonResult<Object> logout() {
//        1.获取SecurityContextHolder里的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails adminUserDetails = (AdminUserDetails) authentication.getPrincipal();
        Long id = adminUserDetails.getUmsAdmin().getId();
//       2.将用户信息从redis删除

        return null;
    }

    /**
     * 用户注册
     */
    @Override
    public int register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

//        查询数据库中是否有存在的用户
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername, umsAdmin.getUsername());
        boolean exists = adminMapper.exists(wrapper);
        if (exists) {
            throw new UsernameNotFoundException("用户已存在");
        }
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        List<UmsPermission> permissionList = umsAdminRoleRelationDao.getPermissionList(adminId);
        return permissionList;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername,username);
        List<UmsAdmin> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
}




