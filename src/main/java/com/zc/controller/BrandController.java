package com.zc.controller;

import com.zc.domian.Brand;
import com.zc.service.BrandService;
import com.zc.utils.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public JsonResult getAll(){
        List<Brand> list = brandService.list();
        return new JsonResult(OK,list);
    }

    @GetMapping("{userid}")
    public JsonResult getByUserId(@PathVariable Integer userid){
        List<Brand> list = brandService.findByuserid(userid);
        return new JsonResult(OK,list);
    }

    @PostMapping
    public JsonResult addone(@RequestBody Brand brand){
        brandService.save(brand);
        return new JsonResult(OK);
    }

    @DeleteMapping("{id}")
    public JsonResult delById(@PathVariable Integer id){
        brandService.removeById(id);
        return new JsonResult(OK);
    }

}
