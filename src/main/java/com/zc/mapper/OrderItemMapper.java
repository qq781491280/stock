package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Order;
import com.zc.domian.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<OrderItem> selectByOrderId(@Param("orderid") String orderid);

}
