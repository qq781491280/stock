package com.zc.mapper;

import com.zc.domian.Files;
import com.zc.domian.Goods;
import com.zc.domian.Order;
import com.zc.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class userInfoMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    FileMapper fileMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Test
    void getAll(){
//        Goods goods = new Goods();
//        goods.setCid(1);
//        goods.setBid(1);
//        goods.setMname("xxzz");
//        Integer addone = goodsMapper.addone(goods);
//        System.out.println(goods.getId());
//        Files files = new Files();
//        files.setLocation("xxxx");
//        files.setDescription("xxxx");
//        files.setOldName("aaa");
//        files.setUid("admin");
//        files.setName("cccc");
//        fileMapper.insertImg(files);
//        System.out.println(files.getId());

//        List<Order> order = orderService.getOrder();
//        System.out.println(order);
        List<Order> bystr = orderMapper.selectBystr("创维");
        System.out.println(bystr);
    }

}
