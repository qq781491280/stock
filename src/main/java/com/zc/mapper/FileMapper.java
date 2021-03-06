package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper extends BaseMapper<Files> {
    List<Files> findGoodsImgByGid(@Param("id") Long id);

    void insertImg(Files files);
    Integer addGoodsImg(@Param("goodsid")Long goodsid,@Param("filesid") Long filesid);

    Integer deleteByurl(String url);

    Integer deleteByimgid(Long filesid);

    Integer deleteByGid(Long goodsid);

}
