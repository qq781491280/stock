package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Brand;
import com.zc.mapper.BrandMapper;
import com.zc.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    BrandMapper brandMapper;
    @Override
    public List<Brand> findByuserid(Integer userid) {
        List<Brand> brands = brandMapper.selectByuserId(userid);
        return brands;
    }
}
