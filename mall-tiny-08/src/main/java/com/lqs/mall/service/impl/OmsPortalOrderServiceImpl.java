package com.lqs.mall.service.impl;

import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.component.CancelOrderSender;
import com.lqs.mall.domain.dto.OrderParam;
import com.lqs.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理Service
 * Created by 刘千山 on 2023/6/20/020-19:12
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult<Object> generateOrder(OrderParam orderParam) {
        //TODO 下单操作
        LOGGER.info("generate order");
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消下订单
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //todo 执行一系类取消订单操作，具体参考mall项目
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    /**
     * 向消息队列发送延迟消息
     */
    private void sendDelayMessageCancelOrder(Long orderId) {
        long delayTimes = 60 * 1000;
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
