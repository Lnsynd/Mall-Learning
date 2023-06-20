package com.lqs.mall.config;

import com.lqs.mall.domain.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘千山 on 2023/6/20/020-17:32
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 订单消息队列交换机
     */
    @Bean
    DirectExchange orderDirect(){
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟消息交换机
     */
    @Bean
    DirectExchange orderTtlDirect(){
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }


    /**
     * 订单消费队列
     */
    @Bean
    public Queue orderQueue(){
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 订单延迟队列
     */
    @Bean
    public Queue orderTtlQueue(){
        return QueueBuilder.durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange()) // 到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey()) // 到期后转发的键
                .build();
    }


    /**
     * 订单消费队列和交换机绑定
     */
    @Bean
    public Binding orderBinding(DirectExchange orderDirect,Queue orderQueue){
        return BindingBuilder.bind(orderQueue).to(orderDirect).with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }


    /**
     *  订单延迟队列和延迟交换机绑定
     */
    @Bean
    public Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue){
        return BindingBuilder.bind(orderTtlQueue).to(orderTtlDirect).with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }


}
