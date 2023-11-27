package com.example.jpabook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemQueryDTO {
    private String itemName;
    private int orderPrice;
    private int count;
}
