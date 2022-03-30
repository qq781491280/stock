package com.zc.domian;

import lombok.Data;
import java.util.Date;

@Data
public class OrderItem {
    private Integer id;
    private String  name;
    private String  sort;
    private Integer count;
    private Double price;
    private Double totalPrice;
    private String orderId;
}
