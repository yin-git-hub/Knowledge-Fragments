package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

//独立降级处理器类
public class RateLimitControllerHandler {

    //兜底方法：降级处理方法
    //方法必须是static的。
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    public static CommonResult handleException3(Long id,BlockException exception){
        return new CommonResult(333,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }


    public static CommonResult handleException2(Long id,Throwable exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务内部异常,稍后访问");
    }
}
