package com.lqs.mall.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时 取消订单 并且解锁库存的定时任务
 * Created by 刘千山 on 2023/6/15/015-11:14
 */
@Component
public class OrderTimeTask {


    @Scheduled(cron ="0 0/10 * ? * ?")
    public void cancelTimeOutOrder(){
        // TODO 执行取消订单的操作

    }
}
