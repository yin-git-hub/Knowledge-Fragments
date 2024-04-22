package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.handler.RateLimitControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RateLimitController{


    /**
     * @SentinelResource  与 Hystrix 组件中的@HystrixCommand注解作用类似的。
     *    value = "byResourceName"  用于设置资源名称，只有根据资源名称设置的规则，才能执行blockHandler所引用降级方法。
     *    如果按照映射路径进行规则配置，返回默认降级消息：Blocked by Sentinel (flow limiting)
     *    blockHandler 用于引用降级方法。
     *    blockHandlerClass 用于引用降级方法的处理器类。注意：降级方法必须是static的。否则，无法解析
     */
/*    @GetMapping("/byResource")
    @SentinelResource(value = "byResourceName",blockHandler = "handleException",
            blockHandlerClass = RateLimitControllerHandler.class
    )
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }*/

    //兜底方法：降级处理方法
    /*public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }*/


    @Autowired
    RestTemplate restTemplate;

    /**
     * @SentinelResource  与 Hystrix 组件中的@HystrixCommand注解作用类似的。
     *    value = "byResourceName"  用于设置资源名称，只有根据资源名称设置的规则，才能执行blockHandler所引用降级方法。
     *    如果按照映射路径进行规则配置，返回默认降级消息：Blocked by Sentinel (flow limiting)
     *    blockHandler 用于引用降级方法。
     *    blockHandlerClass 用于引用降级方法的处理器类。注意：降级方法必须是static的。否则，无法解析
     *    blockHandler + blockHandlerClass 只处理配置违规，进行降级处理。代码出现异常，不执行的。
     *
     *    blockHandler + fallback 同时存在，配置违规，代码也有异常，这时，走blockHandler配置文件降级处理
     *
     *    exceptionsToIgnore 设置特定异常不需要降级处理。
     */
    @RequestMapping("/fallback/{id}")
    @SentinelResource(value = "byFallbackName",blockHandler = "handleException3",
            blockHandlerClass = RateLimitControllerHandler.class,
            fallback = "handleException2",fallbackClass = RateLimitControllerHandler.class,
            exceptionsToIgnore=IllegalArgumentException.class
    )
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }

        if (id==-1) {
            CommonResult<Payment> result = new CommonResult<>(444,"数据不存在",null);
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        CommonResult<Payment> result = new CommonResult<>(200,"数据已经获取",new Payment(id,"test"+1));
        return result;
    }

}
