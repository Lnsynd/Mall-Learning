package com.lqs.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@MapperScan({"com.lqs.mall.mapper","com.lqs.mall.dao"})
@SpringBootApplication
public class MallTiny05Application {

	public static void main(String[] args) {
		SpringApplication.run(MallTiny05Application.class, args);
	}

}
