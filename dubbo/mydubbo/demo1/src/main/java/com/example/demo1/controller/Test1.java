package com.example.demo1.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yin7331
 * Date: 2023/10/22 22:43
 * Describe:
 */
@RestController
public class Test1 {
    @Reference
    private GreetingService greetingService;
    @GetMapping("/test")
    public String test(){
         String s = greetingService.sayHello("wwww");
        return s;
    }
}
