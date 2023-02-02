package com.andy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.andy.mapper")
public class CrmWechatApp
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext run = SpringApplication.run(CrmWechatApp.class, args);
        Object datasource = run.getBean("dataSource");
        System.out.println("欢迎进入webchat app!");
        System.out.println(datasource);
    }
}
