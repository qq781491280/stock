package com.zc.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Sort {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String cname;
    private Integer userid;
}
