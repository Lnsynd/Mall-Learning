package com.lqs.mall.service;

import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.domain.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理
 * Created by 刘千山 on 2023/6/20/020-19:11
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);


    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
