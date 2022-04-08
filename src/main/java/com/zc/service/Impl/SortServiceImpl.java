package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Brand;
import com.zc.domian.Sort;
import com.zc.mapper.SortMapper;
import com.zc.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

    @Autowired
    SortMapper sortMapper;

    @Override
    public List<Sort> findByuserid(Integer userid) {
        List<Sort> Sort = sortMapper.selectByuserId(userid);
        return Sort;
    }
}
