package com.zc.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String  address;
    private Integer roleId;
    private Role role;
}
