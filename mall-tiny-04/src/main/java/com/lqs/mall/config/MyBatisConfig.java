package com.lqs.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘千山 on 2023/6/5/005-19:29
 */

@Configuration
@MapperScan({"com.lqs.mall.dao","com.lqs.mall.mbg.mapper"})
public class MyBatisConfig {
}
