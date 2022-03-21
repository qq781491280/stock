package com.zc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    Goods selectByMname(@Param("mname") String mname);
    List<Goods> selectList();

    int deleteByMname(@Param("mname")  String mname);

    Integer updateBymname(Goods goods);

    Integer updateNumberByName(String mname,String etime, String number);

    Integer updateWtimeNumberByName(String mname,String wtime,String number);
}
