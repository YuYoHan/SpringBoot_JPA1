package com.example.jpabook.api;

import com.example.jpabook.entity.order.Order;
import com.example.jpabook.repository.OrderRepository;
import com.example.jpabook.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
*   xToOne(ManyToOne, OneToOne)
*   Order
*   Order → Member
*   Order → Delivery
*   위에꺼의 최적화
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    // 여기서 보면 List안에 엔티티가 있는데 이러면 안된다.
    // 왜 안되는지를 보여주기 위해서 사용해 본다.
    @GetMapping("/api/v1/simple-orderds")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }
}
