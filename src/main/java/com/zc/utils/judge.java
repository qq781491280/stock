package com.zc.utils;

import java.util.regex.Pattern;

public final class judge {
    public static Boolean Judge(String username,String pwd){
        String regNameStr = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
        String regPwdStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$";
        boolean resultName = Pattern.matches(regNameStr, username);
        boolean resultPwd = Pattern.matches(regPwdStr,pwd);
        if (resultName && resultPwd){
            return true;
        }else {
            return false;
        }
    }
}
