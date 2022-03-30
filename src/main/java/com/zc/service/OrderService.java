package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Goods;
import com.zc.domian.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    String createOrder(Goods goods, Integer userid);
    List<Order> getOrder();

    List<Order> findByOrderId(String  orderid);

    List<Order> findBystr(String str);
}
