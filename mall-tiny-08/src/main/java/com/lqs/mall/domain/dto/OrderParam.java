package com.lqs.mall.domain.dto;

import lombok.Data;

/**
 * Created by 刘千山 on 2023/6/20/020-19:14
 */
@Data
public class OrderParam {
    //收货地址id
    private Long memberReceiveAddressId;
    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;

}
