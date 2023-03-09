package com.andy;

import com.andy.config.AliyunOssProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(AliyunOssProperties.class)
@MapperScan(basePackages = "com.andy.mapper")
public class CrmWebApp
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext run = SpringApplication.run(CrmWebApp.class, args);
        Object datasource = run.getBean("kafkaTemplate");
        System.out.println("欢迎进入web app!");
        System.out.println(datasource);
    }
}
