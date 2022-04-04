package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> selectAll();
    Integer selectToday();
    Integer selectTodayPrice();
    Integer selectToYear();
    Integer selectToWeek();
    Integer selectToMonth();
    List<Order> selectByOrderId(@Param("orderid") String orderid);
    List<Order>selectBystr(@Param("str") String str);
}
