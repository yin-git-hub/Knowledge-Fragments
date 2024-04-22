package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration

public class WebSocketConfig{




    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        System.out.println("配置文件 start");
        return new ServerEndpointExporter();
    }


}
