package com.lqs.mall.controller;

import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.domain.dto.UmsAdminLoginParam;
import com.lqs.mall.domain.pojo.UmsAdmin;
import com.lqs.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘千山 on 2023/6/5/005-19:52
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController", description = "后台用户管理")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登录接口
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public CommonResult<Object> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if(token!=null){
            Map<String,String> map = new HashMap<>();
            map.put("tokenHead",tokenHead);
            map.put("token",token);
            return CommonResult.success(map);
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }



    @PostMapping("/register")
    public CommonResult<Object> register(UmsAdmin umsAdmin){
        adminService.register(umsAdmin);
        if(umsAdmin == null){
            return CommonResult.validateFailed("用户名或者密码错误");
        }
        return CommonResult.success("注册成功!");
    }


    /**
     * 权限测试接口
     */
    @RequestMapping("/logout")
    public CommonResult<Object> loggout(){
        return adminService.logout();
    }


    @PostMapping("/test")
    public CommonResult<String> test(){
        return CommonResult.failed("登录测试失败");
    }

}
