package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Files;
import com.zc.domian.Goods;
import com.zc.domian.Order;
import com.zc.mapper.FileMapper;
import com.zc.mapper.GoodsMapper;
import com.zc.mapper.OrderMapper;
import com.zc.service.GoodsService;
import com.zc.service.OrderService;
import com.zc.service.ex.GoodsException;
import com.zc.service.ex.GoodsNotEmptyException;
import com.zc.utils.FileUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    FileMapper fileMapper;

    @Autowired
    OrderService orderService;

    @Override
    public Goods getBymname(String mname) {
      Goods result =  goodsMapper.selectByMname(mname);
        if (result == null) throw new GoodsNotEmptyException("找不到此商品");
        List<Files> goodsImgByGid = fileMapper.findGoodsImgByGid(result.getId());
            result.setGoodsimg(goodsImgByGid);
        return result;
    }

    @Override
    public List<Goods> getList() {
    List<Goods> result = goodsMapper.selectList();
        for (Goods f: result) {
            Long id = f.getId();
            List<Files> goodsImgByGid = fileMapper.findGoodsImgByGid(id);
            f.setGoodsimg(goodsImgByGid);
        }
        return result;
    }

    @Override
    public void addGoods(Goods goods) {
        Goods mname = goodsMapper.selectByMname(goods.getMname());
        if (mname !=null) throw new GoodsException("商品已存在");

        goods.setNumber(Long.valueOf(0));
        int i = goodsMapper.addone(goods);
        Long goodsId = goods.getId();
        Long[] imgidlsit = goods.getImgidlsit();
        for (Long id:imgidlsit) {
            fileMapper.addGoodsImg(goodsId,id);
    }

        if (i!=1) throw new GoodsException("添加商品时发生未知错误");
    }

    @Override
    public void removeByMname(String mname) {
        Goods goods = goodsMapper.selectByMname(mname);
        if (goods==null) throw  new GoodsException("商品不存在");
        Long id = goods.getId();
        List<Goods> goodsList = goodsMapper.selectimgByid(id);
        List<Files> filesList = fileMapper.findGoodsImgByGid(id);
        for (Goods g:goodsList) {
            fileMapper.deleteByGid(g.getId());
            for(Files files: filesList) {
                FileUtils.deleteFile(files.getDescription());
                fileMapper.deleteByurl(files.getDescription());
            }
        }
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
    public void modfiyByGidGoodsInfo(Goods goods) {
        Goods result = goodsMapper.findOneById(goods.getId());
        if (result==null) throw  new GoodsException("商品不存在");
        goodsMapper.updateByGid(goods);
    }

    @SneakyThrows
    @Override
    public void modfiyNumberByName(Goods goods) {
        Goods result = goodsMapper.findOneById(goods.getId());
        if (result==null) throw new GoodsException("商品不存在");
        result.setNumber(result.getNumber()+goods.getNumber());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String etime = format.format(goods.getEtime());
        Date date = format.parse(etime);
        result.setEtime(date);
        result.setWprice(goods.getWprice());
        goodsMapper.updateNumberByName(result);
    }

    @SneakyThrows
    @Override
    public void modfiyWtimeNumberByName(Goods goods) {
        Goods result = goodsMapper.findOneById(goods.getId());
        if (result==null) throw new GoodsException("商品不存在");
        if (result.getNumber()>goods.getNumber()){
          result.setNumber(result.getNumber()-goods.getNumber());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String wtime = format.format(goods.getWtime());
            Date date = format.parse(wtime);
            result.setWtime(date);
            result.setSprice(goods.getSprice());
            orderService.createOrder(goods,1);
            goodsMapper.updateWtimeNumberByName(result);
        }else {
            throw  new GoodsException("库存不足");
        }

    }

    @Override
    public Goods findoneByid(Long id) {
        Goods oneById = goodsMapper.findOneById(id);
        if (oneById==null) throw new GoodsException("商品不存在");
        List<Files> goodsImgByGid = fileMapper.findGoodsImgByGid(oneById.getId());
        oneById.setGoodsimg(goodsImgByGid);
        return oneById;
    }
}
