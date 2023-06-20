package com.lqs.mall.component;

import com.lqs.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * 用于从取消订单的消息队列（mall.order.cancel）里接收消息
 * Created by 刘千山 on 2023/6/20/020-19:07
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;
    @RabbitHandler
    public void handle(Long orderId){
        omsPortalOrderService.cancelOrder(orderId);
        LOGGER.info("receive delay message orderId:{}",orderId);

    }

}
