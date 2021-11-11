package com.swp.dataweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.swp.dataweb.dao")
public class DataWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWebApplication.class, args);
    }
}
