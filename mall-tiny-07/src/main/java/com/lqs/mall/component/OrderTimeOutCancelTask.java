package com.lqs.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时自动取消定时任务
 * Created by 刘千山 on 2023/6/20/020-15:03
 */
@Component
public class OrderTimeOutCancelTask {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);


    @Scheduled(cron = "0 0/10 * ? * ?")
    public void cancelOrder(){
        // 取消订单
        LOGGER.info("取消订单，释放库存");
    }
}
