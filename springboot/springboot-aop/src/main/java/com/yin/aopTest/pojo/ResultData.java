package com.yin.aopTest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData<T> {

    private Integer errCode;

    private String errMsg;

    private T data;

    public static ResultData success(){
        return new ResultData(0, "", null);
    }

    public static ResultData fail(String errMsg){
        return new ResultData(-1, errMsg, null);
    }

    public static <T> ResultData success(T t) {
        return new ResultData(0, "成功", t);
    }
}
