package com.zc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Goods;
import com.zc.domian.Role;
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

    Integer updateNumberByName(Goods goods);

    Integer updateWtimeNumberByName(Goods goods);

    Integer updateByGid(Goods goods);

    Integer addone(Goods goods);

    Goods findOneById(@Param("id") Long id);

    List<Goods> selectimgByid(@Param("id") Long id);

    List<Goods> selectByUserId(@Param("userid") Integer userid);
}
