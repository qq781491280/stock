package com.zc.controller;

import com.zc.domian.*;
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
    public  JsonResult search(String str,Integer userid){
        List<Order> list =   orderService.findBystr(str,userid);
        return new JsonResult(OK,list);
    }

    @RequestMapping("/statistics")
    @ResponseBody
    public  JsonResult statistics(Integer userid){
        Statistics statistics = orderService.getStatistics(userid);
        return new JsonResult(OK,statistics);
    }

    @RequestMapping("/week")
    @ResponseBody
    public  JsonResult getWeek(Integer userid){
        List<Tongji> week = orderService.getWeek(userid);
        return new JsonResult(OK,week);
    }

    @RequestMapping("/mount")
    @ResponseBody
    public  JsonResult getMount(Integer userid){
        List<Tongji> week = orderService.getMount(userid);
        return new JsonResult(OK,week);
    }

    @RequestMapping("/year")
    @ResponseBody
    public  JsonResult getYear(Integer userid){
        List<Tongji> week = orderService.getYear(userid);
        return new JsonResult(OK,week);
    }

    @RequestMapping("/findbyuserid")
    @ResponseBody
    public  JsonResult findbyuserid(@RequestParam("userid") Integer userid){
        List<Order> list = orderService.getOrderByUserId(userid);
        return new JsonResult(OK,list);
    }

}
