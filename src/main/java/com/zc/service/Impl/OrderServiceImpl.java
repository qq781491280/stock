package com.zc.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.*;
import com.zc.mapper.GoodsMapper;
import com.zc.mapper.OrderItemMapper;
import com.zc.mapper.OrderMapper;
import com.zc.mapper.RukuOrderMapper;
import com.zc.service.OrderService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
 private  OrderMapper orderMapper;

    @Autowired
    private  OrderItemMapper orderItemMapper;

    @Autowired
    private RukuOrderMapper rukuOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @SneakyThrows
    @Override
    public String createOrder(Goods goods, Integer userid) {
        //订单号===唯一性 时间戳
        String orderId = System.currentTimeMillis()+userid+"";
        Goods result = goodsMapper.findOneById(goods.getId());
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.setOrderId(orderId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        Date createtime = format.parse(time);
        order.setCreateTime(createtime);
        order.setPrice(goods.getSprice()*goods.getNumber());
        order.setUserid(userid);
        orderMapper.insert(order);
        orderItem.setName(result.getBrand().getPinpai()+result.getMname());
        orderItem.setSort(result.getSort().getCname());
        orderItem.setOrderId(orderId);
        orderItem.setCount(Math.toIntExact(goods.getNumber()));
        orderItem.setTotalPrice(goods.getSprice()*goods.getNumber());
        orderItem.setPrice(goods.getSprice());
        orderItemMapper.insert(orderItem);
        return orderId;
    }

    @SneakyThrows
    @Override
    public String createRukuOrder(Goods goods, Integer userid) {
        //订单号===唯一性 时间戳
        String orderId = System.currentTimeMillis()+userid+"";
        Goods result = goodsMapper.findOneById(goods.getId());
        RukuOrder rukuOrder = new RukuOrder();
        rukuOrder.setRukuId(orderId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        Date createtime = format.parse(time);
        rukuOrder.setCreateTime(createtime);
        rukuOrder.setPrice(goods.getWprice());
        rukuOrder.setUserid(userid);
        rukuOrder.setName(result.getBrand().getPinpai()+result.getMname());
        rukuOrder.setCount(Math.toIntExact(goods.getNumber()));
        rukuOrder.setTotalPrice(goods.getWprice()*goods.getNumber());
        rukuOrder.setPrice(goods.getSprice());
        rukuOrderMapper.insert(rukuOrder);
        return orderId;
    }

    @Override
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        Integer today = orderMapper.selectToday();
        Integer todayPrice = orderMapper.selectTodayPrice();
        Integer toWeek = orderMapper.selectToWeek();
        Integer toMonth = orderMapper.selectToMonth();
        Integer toYear = orderMapper.selectToYear();
        Integer rukutoday = rukuOrderMapper.selectToday();
        statistics.setToday(today);
        statistics.setWeek(toWeek);
        statistics.setMonth(toMonth);
        statistics.setYear(toYear);
        statistics.setTodayPrice(todayPrice);
        statistics.setRukutoday(rukutoday);
        return statistics;
    }

    @Override
    public List<Order> getOrder() {
        List<Order> orders = orderMapper.selectAll();
        for (Order o: orders) {
            String orderId = o.getOrderId();
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
            o.setOrderItems(orderItems);
        }
        return orders;
    }

    @Override
    public List<Order> findByOrderId(String orderid) {
        List<Order> orders = orderMapper.selectByOrderId(orderid);
        for (Order o: orders) {
            String orderId = o.getOrderId();
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
            o.setOrderItems(orderItems);
        }
        return orders;
    }

    @Override
    public List<Order> findBystr(String str) {
        List<Order> orders = orderMapper.selectBystr(str);
        if (str==""||str==null){
            this.getOrder();
        }else {
            for (Order o: orders) {
                String orderId = o.getOrderId();
                List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
                o.setOrderItems(orderItems);
            }
        }
        return orders;
    }
}
