package com.zc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.User;

import java.util.List;

public interface UserService extends IService<User> {

   List<User> UserList();
   User getByUsername(String username);
//   Boolean getByUsernameAndPassword(String username, String password);
   Boolean modfiy(User user);
    User login(String username, String password);
    Boolean register(User user);
   void modfiypassword(String username, String newpassword, String oldpassword);
}