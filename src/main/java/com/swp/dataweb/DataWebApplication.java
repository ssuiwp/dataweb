package com.swp.dataweb;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@MapperScan("com.swp.dataweb.dao")
public class DataWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWebApplication.class, args);
    }
}
