package com.lqs.mall.controller;

import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.nosql.mongodb.document.MemberReadHistory;
import com.lqs.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 刘千山 on 2023/6/20/020-15:46
 */
@RestController
@RequestMapping("/member/readHistory")
@Api(tags = "MemberReadHistoryController",description = "用户商品浏览记录管理")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;


    @ApiOperation("创建浏览记录")
    @PostMapping("/create")
    public CommonResult<Object> create(@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }



    @ApiOperation("删除浏览记录")
    @PostMapping("/delete")
    public CommonResult<Object> delete(List<String> ids){
        int delete = memberReadHistoryService.delete(ids);
        if(delete>0){
            return CommonResult.success(delete);
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取浏览记录")
    @GetMapping("/list")
    public CommonResult<List<MemberReadHistory>> list(Long memberId){
        return CommonResult.success(memberReadHistoryService.list(memberId));
    }


}
