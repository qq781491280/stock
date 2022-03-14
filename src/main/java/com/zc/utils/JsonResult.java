package com.zc.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    private Integer state;
    private  String message;
    private  E data;

    public JsonResult(){}

    public JsonResult(Integer state){
        this.state = state;
        this.message = "success";

    }

    /*出现异常时调用*/
    public  JsonResult(Throwable e){
        //获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public JsonResult (Integer state,E data){
        this.state= state;
        this.message = "success";
        this.data = data;
    }



}
