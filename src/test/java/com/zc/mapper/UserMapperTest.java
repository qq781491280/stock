package com.zc.mapper;


import com.zc.domian.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    void selectById(){
        System.out.println(userMapper.selectById(1));
    }
    @Test
    void setUser(){
        User user = new User();
        user.setUsername("zc123");
        user.setPassword("123");
        user.setRoleid(1);
        System.out.println(userMapper.insert(user));
    }
}
