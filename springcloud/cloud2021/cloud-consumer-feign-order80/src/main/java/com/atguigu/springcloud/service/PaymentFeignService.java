package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign接口声明：
 *      方法与调用服务的controlelr方法保持一致。
 */
@Component //底层声明接口的代理类对象
@FeignClient("CLOUD-PAYMENT-SERVICE") //调用哪一个服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment);
}
