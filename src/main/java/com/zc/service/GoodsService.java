package com.zc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Files;
import com.zc.domian.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    Goods getBymname(String mname);
    List<Goods> getList();

    List<Goods> getByUserid(Integer userid);

    void addGoods(Goods goods);

    void removeByMname(String mname);

    void modfiyGoodsInfo(Goods goods);

    void modfiyByGidGoodsInfo(Goods goods);

    void modfiyNumberByName(Goods goods);

    void modfiyWtimeNumberByName(Goods goods);

    Goods  findoneByid(Long id);

}
