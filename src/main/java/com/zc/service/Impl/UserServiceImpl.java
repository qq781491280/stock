package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.User;
import com.zc.mapper.UserMapper;
import com.zc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private  UserMapper userMapper;


    @Override
    public List<User> UserList() {
        return userMapper.selectUserList();
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Boolean existsUsername(String username) {
        if (userMapper.selectByUsername(username)==null){
            return false;
        }
        return true;
    }

    @Override
    public Boolean getByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public Boolean modfiy(User user) {
        return userMapper.updateByUsername(user);
    }
}
