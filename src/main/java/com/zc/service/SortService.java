package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Brand;
import com.zc.domian.Sort;

import java.util.List;

public interface SortService extends IService<Sort> {
    List<Sort> findByuserid(Integer userid);
}
