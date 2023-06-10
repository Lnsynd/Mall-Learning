package com.lqs.mall.controller;

import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.domain.dto.UmsAdminLoginParam;
import com.lqs.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
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

    @PostMapping("/login")
    public CommonResult<Object> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if(token!=null){
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("tokenHead",tokenHead);
            return CommonResult.success(map);
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }


    @PostMapping("/test")
    public CommonResult<String> test(){
        return CommonResult.failed("登录测试失败");
    }

}
