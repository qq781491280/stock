package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Goods;
import com.zc.domian.Order;
import com.zc.domian.Statistics;
import com.zc.domian.Tongji;

import java.util.List;

public interface OrderService extends IService<Order> {

    String createOrder(Goods goods, Integer userid);

    String createRukuOrder(Goods goods, Integer userid);

    Statistics getStatistics(Integer userid);

    List<Tongji> getWeek(Integer userid);

    List<Tongji> getMount(Integer userid);

    List<Tongji> getYear(Integer userid);

    List<Order> getOrder();

    List<Order> findByOrderId(String  orderid);

    List<Order> findBystr(String str,Integer userid);

    List<Order> getOrderByUserId(Integer userid);
}
