package com.lqs.malltiny01.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.lqs.malltiny01.mbg.mapper")
public class MyBatisConfig {
}