package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderFeignController {

    @Autowired
    PaymentFeignService paymentFeignService; //Feign接口，用于远程调用。

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody  Payment payment){
        CommonResult result = paymentFeignService.create(payment);

        return result;
    }
}
