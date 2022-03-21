package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Goods;
import com.zc.mapper.GoodsMapper;
import com.zc.service.GoodsService;
import com.zc.service.ex.GoodsException;
import com.zc.service.ex.GoodsNotEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Goods getBymname(String mname) {
      Goods result =  goodsMapper.selectByMname(mname);
      if (result == null) throw new GoodsNotEmptyException("找不到此商品");
        return result;
    }

    @Override
    public List<Goods> getList() {
    List<Goods> result = goodsMapper.selectList();
        return result;
    }

    @Override
    public void addGoods(Goods goods) {
        Goods mname = goodsMapper.selectByMname(goods.getMname());
        if (mname !=null) throw new GoodsException("商品已存在");
        int i = goodsMapper.insert(goods);
        if (i!=1) throw new GoodsException("添加商品时发生未知错误");
    }

    @Override
    public void removeByMname(String mname) {
        Goods goods = goodsMapper.selectByMname(mname);
        if (goods==null) throw  new GoodsException("商品不存在");
        int i = goodsMapper.deleteByMname(mname);
        if (i!=1) throw  new GoodsException("删除商品是发生未知错误");
    }

    @Override
    public void modfiyGoodsInfo(Goods goods) {
        Goods result = goodsMapper.selectByMname(goods.getMname());
        if (result==null) throw  new GoodsException("商品不存在");
        goodsMapper.updateBymname(goods);
    }

    @Override
    public void modfiyNumberByName(String mname,String etime, String number) {
        Goods result = goodsMapper.selectByMname(mname);
        if (result==null) throw new GoodsException("商品不存在");
        goodsMapper.updateNumberByName(mname,etime,number);
    }

    @Override
    public void modfiyWtimeNumberByName(String mname, String wtime, String number) {
        Goods result = goodsMapper.selectByMname(mname);
        if (result==null) throw new GoodsException("商品不存在");
        goodsMapper.updateWtimeNumberByName(mname,wtime,number);
    }
}
