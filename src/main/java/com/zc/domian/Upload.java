package com.zc.domian;

import lombok.Data;

import java.util.Date;


@Data
public class Upload {
    private Long id;

    //存在本地的地址
    private String location;

    //名称，业务中用到的名称，比如 ”档案模板“、”用户信息“、”登录记录“等等
    private String name;

    //保留文件原来的名字
    private String oldName;

    //描述（可以为空）
    private String description;

    private Date createTime;

    private Date updateTime;

    private String uid;

    private User user;
}
