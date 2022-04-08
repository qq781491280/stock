package com.zc.controller;

import com.zc.domian.Sort;
import com.zc.service.SortService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortController extends BaseController {

    @Autowired
    SortService sortService;

    @GetMapping
    public JsonResult getAll(){
        List<Sort> list = sortService.list();
        return new JsonResult(OK,list);
    }

    @GetMapping("{userid}")
    public JsonResult getByUserId(@PathVariable Integer userid){
        List<Sort> list = sortService.findByuserid(userid);
        System.out.println(userid);
        return new JsonResult(OK,list);
    }

    @PostMapping
    public JsonResult addone(@RequestBody Sort sort){
        sortService.save(sort);
        return new JsonResult(OK);
    }

    @DeleteMapping("{id}")
    public JsonResult delById(@PathVariable Integer id){
        sortService.removeById(id);
        return new JsonResult(OK);
    }

}
