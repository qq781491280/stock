package com.zc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Sort;
import com.zc.mapper.SortMapper;
import com.zc.service.SortService;
import org.springframework.stereotype.Service;

@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {
}
