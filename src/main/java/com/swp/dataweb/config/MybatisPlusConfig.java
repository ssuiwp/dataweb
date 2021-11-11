package com.swp.dataweb.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//什么是bean spring容器管理的对象叫做bean
@Configuration //标识这是一个配置类 相当于早期的xml文件
public class MybatisPlusConfig {

    /**
     * MP生命周期方法:  itemMapper.selectPage--自动调用MybatisPlusInterceptor对象
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor
                (new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}