package com.zc.utils;

import lombok.Data;

@Data
public class Rs {
    private Boolean flag;
    private String msg;

    public Rs(){};

    public Rs(Boolean flag){
        this.flag = flag;
    }
    public Rs(Boolean flag, String msg){
        this.flag = flag;
        this.msg = msg;
    }

}
