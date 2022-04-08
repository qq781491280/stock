package com.zc.controller;

import com.zc.domian.Goods;
import com.zc.service.GoodsService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    GoodsService goodsService;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping
    public JsonResult findAll(){
        List<Goods> list = goodsService.getList();
        return new JsonResult(OK,list);
    }

    @RequestMapping("/findByUserId")
    @ResponseBody
    public JsonResult findByUserId(@RequestParam("userid") Integer userid){
        List<Goods> list = goodsService.getByUserid(userid);
        return new JsonResult(OK,list);
    }

    /**
     * 按信息查询商品
     * @param mname
     * @return
     */
    @GetMapping("{mname}")
    public JsonResult findBymname (@PathVariable String mname){
       Goods goods = goodsService.getBymname(mname);
        return new JsonResult(OK,goods);
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping
    public JsonResult saveGoods(@RequestBody Goods goods) {
        goodsService.addGoods(goods);
        return new JsonResult(OK);
    }

    /**
     * 删除商品
     * @param mname
     * @return
     */
    @DeleteMapping("{mname}")
    public JsonResult delGoods(@PathVariable String mname ){
        goodsService.removeByMname(mname);
        return new JsonResult(OK);
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    @PutMapping
    public JsonResult updateGoodsInfo(@RequestBody Goods goods){
        goodsService.modfiyByGidGoodsInfo(goods);
        return new JsonResult(OK);
    }

    @RequestMapping("/updateNumber")
    @ResponseBody
    public JsonResult updateNumber(@RequestBody Goods goods){
        goodsService.modfiyNumberByName(goods);
        return new JsonResult(OK);
    }

    @RequestMapping("/updatewtime")
    @ResponseBody
    public JsonResult updatewtime(@RequestBody Goods goods){
        goodsService.modfiyWtimeNumberByName(goods);
        return new JsonResult(OK);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public JsonResult findById (@RequestParam("id") Long id){
        Goods goods = goodsService.findoneByid(id);
        return new JsonResult(OK,goods);
    }

}
