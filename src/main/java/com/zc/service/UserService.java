package com.zc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.User;

import java.util.List;

public interface UserService extends IService<User> {

   List<User> UserList();
}
