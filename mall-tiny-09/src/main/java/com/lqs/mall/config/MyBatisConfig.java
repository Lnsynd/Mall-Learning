package com.lqs.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘千山 on 2023/6/28/028-14:57
 */
@Configuration
@MapperScan({"com.lqs.mall.mbg.mapper","com.lqs.mall.dao"})
public class MyBatisConfig{
}
