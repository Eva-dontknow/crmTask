package com.nast.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.nast.crm.dao")//指定dao接口所在的包，目的是在程序运行时为这些接口创建动态代理对象
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
