package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Order;
import com.zc.domian.Tongji;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> selectAll();
    Integer selectToday(Integer userid);
    Integer selectTodayPrice(Integer userid);
    Integer selectToYear(Integer userid);
    Integer selectToWeek(Integer userid);
    Integer selectToMonth(Integer userid);
    List<Tongji>selecttongjitoday(Integer userid);
    List<Tongji>selecttongjitomonth(Integer userid);
    List<Tongji>selecttongjiyear(Integer userid);
    List<Order> selectByOrderId(@Param("orderid") String orderid);
    List<Order>selectBystr(@Param("str") String str,@Param("str")Integer userid);

    List<Order> selectByOrderByUserId(Integer userid);
}
