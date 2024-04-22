package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")  //   http://nacos-payment-provider
    String PATH ;

    @RequestMapping("/getInfo/{id}")
    public String getInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PATH+"/payment/nacos/"+id,String.class);
    }
}
