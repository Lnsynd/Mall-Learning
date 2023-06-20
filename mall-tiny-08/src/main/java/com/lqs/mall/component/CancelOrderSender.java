package com.lqs.mall.component;

import com.lqs.mall.domain.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 向延迟消息队列发消息,取消订单的发送者
 * Created by 刘千山 on 2023/6/20/020-18:54
 */
@Component
public class CancelOrderSender {

    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 发送消息
     * @param orderId 订单编号
     * @param delayTimes 延迟时间
     */
    public void sendMessage(Long orderId,final long delayTimes){
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        // 消息设置延迟毫秒值
                        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                        return message;
                    }
                });
        LOGGER.info("send delay message orderId:{}",orderId);
    }
}
