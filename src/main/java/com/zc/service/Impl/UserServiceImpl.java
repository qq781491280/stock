package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.User;
import com.zc.mapper.FileMapper;
import com.zc.mapper.UserMapper;
import com.zc.service.UserService;
import com.zc.service.ex.*;
import com.zc.utils.judge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        // 从查询结果中获取盐值
      String salt = result.getSalt();
      // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
      String md5Password = getMd5Password(password, salt);
      // 判断查询结果中的密码，与以上加密得到的密码是否不一致
     if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
        throw new PasswordNotMatchException("密码验证失败的错误");
}
//        if (!userPassword.equals(password)) throw new PasswordNotMatchException("密码错误");
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
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setRoleId(2);
        user.setUserimg("http://127.0.0.1:80/api/file/toux.png");
        int i = userMapper.insert(user);
        if (i!=1) throw  new InsertException("注册时发生了未知的错误");
        return i==1;
    }

    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password +
                    salt).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     * 修改密码
     * @param username
     * @param newpassword
     * @param oldpassword
     */
    @Override
    public void modfiypassword(String username, String newpassword, String oldpassword) {
        User result = userMapper.selectByUsername(username);
        String userPassword = result.getPassword();
        if (result==null) throw  new UsernameDuplicateException("用户名不存在");
        // 从查询结果中获取盐值
        String oldsalt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String oldmd5Password = getMd5Password(oldpassword, oldsalt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(oldmd5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("密码验证失败的错误");
        } else {
            if (!judge.JudgePwd(newpassword)){
                throw new VerifyPasswordException("新密码不规范");
            }else {
                if (newpassword.equals(oldpassword)){
                    throw new VerifyPasswordException("新旧密码不能相同");
                }else {
                    // 补全数据：加密后的密码
                    String newsalt = UUID.randomUUID().toString().toUpperCase();
                    String newmd5Password = getMd5Password(newpassword, newsalt);
                    Integer i = userMapper.updatePasswordByUsername(username, newmd5Password,oldmd5Password);
                    if (i!=1) throw new InsertException("修改密码时发生了未知错误");
                }

            }

        }

    }




}
