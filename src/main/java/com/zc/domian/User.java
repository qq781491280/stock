package com.zc.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String  userInfoName;
    private String password;
    private String salt;
    private String  sex;
    private Long  phoneNumber;
    private String  email;
//    @JsonFormat(pattern = "yyyy-MM-dd")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String  address;
    private Integer roleId;
    private String  userimg;
    private Role role;
}
