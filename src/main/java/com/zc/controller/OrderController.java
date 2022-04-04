package com.zc.controller;

import com.zc.domian.Files;
import com.zc.domian.Goods;
import com.zc.domian.Order;
import com.zc.domian.Statistics;
import com.zc.service.OrderService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public JsonResult findAll(){
        List<Order> list = orderService.getOrder();
        return new JsonResult(OK,list);
    }

    @GetMapping("{orderid}")
    public JsonResult findByOrderId(@PathVariable String orderid){
        List<Order> list = orderService.findByOrderId(orderid);
        return new JsonResult(OK,list);
    }
    @RequestMapping("/search")
    @ResponseBody
    public  JsonResult search(String str){
        List<Order> list =   orderService.findBystr(str);
        return new JsonResult(OK,list);
    }

    @RequestMapping("/statistics")
    @ResponseBody
    public  JsonResult statistics(){
        Statistics statistics = orderService.getStatistics();
        return new JsonResult(OK,statistics);
    }

}
