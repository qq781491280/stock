package com.zc.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Goods {

    @TableId(type = IdType.AUTO)
    private Long id;
    //分类id
    private Integer cid;

    //分类具体信息
    private Sort sort;

    //品牌id
    private Integer bid;

    //品牌信息
    private Brand brand;

    //名字
    private String  mname;

    //存库量
    private Long number;

    //进货价格
    private BigDecimal wprice;

    //出货价格
    private BigDecimal sprice;

    //入库时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date etime;

    //出库时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date wtime;

    //图片
    private List<Files> goodsimg;

    //
    private Long[] imgidlsit;

}

