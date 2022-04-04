package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Goods;
import com.zc.domian.Order;
import com.zc.domian.Statistics;

import java.util.List;

public interface OrderService extends IService<Order> {

    String createOrder(Goods goods, Integer userid);

    String createRukuOrder(Goods goods, Integer userid);

    Statistics getStatistics();

    List<Order> getOrder();

    List<Order> findByOrderId(String  orderid);

    List<Order> findBystr(String str);
}
