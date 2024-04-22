package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        CommonResult result = restTemplate
                .getForObject(
                        PAYMENT_URL+"/payment/get/"+id,
                        CommonResult.class);

        if(result.getCode()==200){
            System.out.println("200result = " + result);
            return result;
        }else{
            System.out.println("444result = " + result);
            return result;
        }
    }


    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment>   create(@RequestBody Payment payment){
        CommonResult<Payment> result = restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);  //写操作

        return result;
    }


    //==> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }

}
