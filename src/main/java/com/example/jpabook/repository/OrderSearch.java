package com.example.jpabook.repository;

import com.example.jpabook.entity.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    private String memberName;          // 뢰원이름
    private OrderStatus orderStatus;

}
