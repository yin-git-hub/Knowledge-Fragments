package com.example.server;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.GreetingService;


// 服务提供者实现类
@Service
public class GreetingServiceImpl implements GreetingService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
