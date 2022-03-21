package com.zc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Goods;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    Goods getBymname(String mname);
    List<Goods> getList();

    void addGoods(Goods goods);

    void removeByMname(String mname);

    void modfiyGoodsInfo(Goods goods);

    void modfiyNumberByName(String mname,String etime, String number);

    void modfiyWtimeNumberByName(String mname,String wtime,String number);
}
