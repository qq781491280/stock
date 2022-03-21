package com.zc.mapper;


import com.zc.domian.Goods;
import com.zc.domian.User;
import com.zc.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void selectById(){
        System.out.println(userMapper.selectById(1));
    }
    @Test
    void setUser(){
//        User user = new User();
//        user.getUsername("zc123");
//        user.setPassword("123");
//        user.setRoleid(1);
//        System.out.println(userMapper.insert(user));
        goodsMapper.selectByMname("G502");

    }
}
