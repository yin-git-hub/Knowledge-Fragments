package com.yin.aopTest.controller;

import com.yin.aopTest.aop.MyTestAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class AOPTest {

    @GetMapping("/aop/{v}")
    @MyTestAspect(value1 = "aqsqswqd")
    public String aopTest(@PathVariable String v){

        log.info("这是 AOP: {}",v);

        return "yes";
    }

}
