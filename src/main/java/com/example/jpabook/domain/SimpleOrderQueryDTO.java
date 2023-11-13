package com.example.jpabook.domain;

import com.example.jpabook.entity.member.Address;
import com.example.jpabook.entity.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderQueryDTO {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderQueryDTO(Long orderId,
                               String name,
                               LocalDateTime orderDate,
                               OrderStatus orderStatus,
                               Address address) {
        this.orderId = orderId;
        this.name = name; // Lazy 초기화
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address; // Lazy 초기화
    }
}
