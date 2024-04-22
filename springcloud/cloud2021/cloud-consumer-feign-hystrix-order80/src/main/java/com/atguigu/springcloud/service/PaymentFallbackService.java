package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component //降级处理组件类，给每一个远程方法都增加了一个降级方法。远程调用出现问题，就走降级方法。
public class PaymentFallbackService implements PaymentHystrixFeignService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "调用PaymentFallbackService-paymentInfo_OK方法出问题了,降级处理";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "调用PaymentFallbackService-paymentInfo_TimeOut方法出问题了,降级处理";
    }
}
