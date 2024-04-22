package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//配置中心数据变化，自动更新属性。
@RefreshScope   //通过SpringCould原生注解@RefreshScope实现配置自动更新
public class ConfigClientController{


    //配置信息从配置中心进行拉取。
    @Value("${config.info}") //对应nacos配置:nacos-config-client-dev.yaml
    private String configInfo;
    @Value("${db.url}")
    String dbUrl;
    @Value("${db.username}")
    String dbUsername;
    @Value("${db.password}")
    String dbPassword;
    @Value("${db.driver}")
    String dbDriver;

    @GetMapping("/getInfo")
    public String getInfo() {
        System.out.println(
                " "+dbUrl+
                        " "+dbUsername+
                        " "+dbPassword+
                        " "+dbDriver
        );

        return "11";
    }
}
