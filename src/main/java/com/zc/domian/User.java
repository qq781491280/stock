package com.zc.domian;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String  userInfoName;
    private String password;
    private String  sex;
    private Long  phoneNumber;
    private String  email;
    private Date birthday;
    private String  address;
//    private Integer roleid;
    private Role role;
}
