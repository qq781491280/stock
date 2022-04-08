package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Brand;
import com.zc.domian.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SortMapper extends BaseMapper<Sort> {
    List<Sort> selectByuserId(Integer userid);
}
