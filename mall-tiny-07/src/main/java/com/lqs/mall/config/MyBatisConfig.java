package com.lqs.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by 刘千山 on 2023/6/20/020-14:42
 */
@Configuration
@MapperScan({"com.lqs.mall.mapper","com.lqs.mall.dao"})
public class MyBatisConfig {
}
