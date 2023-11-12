package com.example.jpabook.api;

import com.example.jpabook.entity.member.Address;
import com.example.jpabook.entity.order.Order;
import com.example.jpabook.entity.order.OrderStatus;
import com.example.jpabook.repository.OrderRepository;
import com.example.jpabook.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        for(Order order : all) {
            // LAZY 강제 초기화
            order.getMember().getUserName();
            order.getDelivery().getAddress();
        }
        return all;
    }


    @GetMapping("/api/v2/simple-orderds")
    public List<SimpleOrderDTO> orderV2() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDTO> collect = all.stream()
                .map(SimpleOrderDTO::new)
                .collect(Collectors.toList());
        return collect;
    }
    @Data
    static class SimpleOrderDTO {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDTO(Order order) {
            orderId = order.getId();
            name = order.getMember().getUserName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
