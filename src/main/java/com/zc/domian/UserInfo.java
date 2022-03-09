package com.zc.domian;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserInfo {
    private Integer id;
    private String  uid;
    private String  userInfoName;
    private String  sex;
    private String  phoneNumber;
    private String  email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    birthday;
    private String  address;
}
