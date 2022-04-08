package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.RukuOrder;
import com.zc.domian.Tongji;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RukuOrderMapper extends BaseMapper<RukuOrder> {

   Integer selectToday(Integer userid);
   List<Tongji> selecttongjitoday(Integer userid);
   List<Tongji>selecttongjitomonth(Integer userid);
   List<Tongji>selecttongjiyear(Integer userid);
}
