package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.User;
import com.zc.mapper.UserMapper;
import com.zc.service.UserService;
import com.zc.service.ex.*;
import com.zc.utils.judge;
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
        User result = userMapper.selectByUsername(username);
        if (result==null) throw new UserNotFoundException("用户不存在");
        return result;
    }

    @Override
    public Boolean modfiy(User user) {
        User result = userMapper.selectByUsername(user.getUsername());
        if (result==null) throw new UserNotFoundException("用户不存在");
        int i = userMapper.updateByUsername(user);
        if (i!=1) throw  new InsertException("更新时发生未知错误");
        return i==1;

    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User result = userMapper.selectByUsername(username);
        if (result==null) throw new UserNotFoundException("用户数据不存在的错误");
        String userPassword = result.getPassword();
        if (!userPassword.equals(password)) throw new PasswordNotMatchException("密码错误");
        return result;
    }
    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public Boolean register(User user) {
        User result = userMapper.selectByUsername(user.getUsername());
        if (result!=null) throw  new UsernameDuplicateException("用户名已存在");
        if (!judge.JudgeUsername(user.getUsername())) throw  new VerifyUsernameException("帐号不规范");
        if (!judge.JudgePwd(user.getPassword())) throw  new VerifyPasswordException("密码不规范");
        user.setRoleId(2);
        int i = userMapper.insert(user);
        if (i!=1) throw  new InsertException("注册时发生了未知的错误");
        return i==1;
    }

    @Override
    public void modfiypassword(String username, String newpassword, String oldpassword) {
        User result = userMapper.selectByUsername(username);
        String userPassword = result.getPassword();
        if (result==null) throw  new UsernameDuplicateException("用户名不存在");
        if (!userPassword.equals(oldpassword)){
          throw  new PasswordNotMatchException("旧密码不正确");
        }else {
            if (!judge.JudgePwd(newpassword)){
                throw new VerifyPasswordException("新密码不规范");
            }else {
                Integer i = userMapper.updatePasswordByUsername(username, newpassword,oldpassword);
                if (i!=1) throw new InsertException("修改密码时发生了未知错误");
            }

        }

    }
}
