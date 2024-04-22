package com.atguigu.design.structural.bridge;

/**
 * 抽象销售渠道
 * PhoneOnSale  ==howToSale
 * PhoneOffSale == howToSale
 * PhoneStudentSale = howToSale
 * PhonePDD == howToSale
 *
 *
 */
public abstract class AbstractSale {

    // 线上 // 线下
    private String type;
    // 线上 、线下的价格
    private Integer price;
    public AbstractSale(String type,Integer price){
        this.type = type;
        this.price = price;
    }

    String getSaleInfo(){
        return "渠道："+type+"==>"+"价格："+price;
    }

    void howToSale(){
        //都不一样
    }
}
