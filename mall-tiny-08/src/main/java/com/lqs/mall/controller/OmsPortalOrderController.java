package com.lqs.mall.controller;

import com.lqs.mall.domain.dto.OrderParam;
import com.lqs.mall.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘千山 on 2023/6/20/020-19:23
 */
@RestController
@RequestMapping("/order")
@Api(tags = "OmsPortalOrderController",description = "前台订单管理")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息产生订单")
    @PostMapping("/generateOrder")
    public Object generateOrder(@RequestBody OrderParam orderParam){
        return omsPortalOrderService.generateOrder(orderParam);
    }

}
