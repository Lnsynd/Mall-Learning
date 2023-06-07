package com.lqs.mall.service.impl;

import com.lqs.mall.common.utils.JwtTokenUtil;
import com.lqs.mall.dao.UmsAdminRoleRelationDao;
import com.lqs.mall.mbg.mapper.UmsAdminMapper;
import com.lqs.mall.mbg.model.UmsAdmin;
import com.lqs.mall.mbg.model.UmsAdminExample;
import com.lqs.mall.mbg.model.UmsPermission;
import com.lqs.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 刘千山 on 2023/6/5/005-19:53
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    // 创建日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        // 构造Admin的查询条件
        UmsAdminExample example = new UmsAdminExample();
        // 查询用户名为username的用户
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        // 如果 列表不为空 返回
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        // 1.将前端传来的只含有用户名和密码的 用户类 转换为 包含全部属性的UmsAdmin类
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        // 2. 设置用户的创建时间 和 账号的状态
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        // 3.查询是否有相同用户名的用户存在
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList.size() > 0) {
            return null;
        }
        // 4.对密码进行加密
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        // 1.设置 token
        String token = null;
        try {
            // 2.从用户名获取信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // 3.1如果密码不相同
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            // 3.2 密码比较通过时
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }




}
