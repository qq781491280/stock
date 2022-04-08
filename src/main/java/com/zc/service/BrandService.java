package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {
    List<Brand> findByuserid(Integer userid);
}
