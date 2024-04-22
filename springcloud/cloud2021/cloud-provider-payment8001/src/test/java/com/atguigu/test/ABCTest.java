package com.atguigu.test;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ABCTest {
    public static void main(String[] args) throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.getYear());
        //2021-09-15T10:10:26.296+08:00[Asia/Shanghai]
    }
}
