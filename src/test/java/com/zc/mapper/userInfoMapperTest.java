package com.zc.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userInfoMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void getAll(){
        System.out.println(userMapper.selectUserList());
    }

}
