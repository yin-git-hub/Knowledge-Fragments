package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //声明Eureka服务器，注册中心。
public class Application7001 {
    public static void main(String[] args) {
        SpringApplication.run(Application7001.class);
    }
}
