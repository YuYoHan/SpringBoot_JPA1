package com.example.jpabook.api;

import com.example.jpabook.entity.member.Address;
import com.example.jpabook.entity.order.Order;
import com.example.jpabook.entity.order.OrderItem;
import com.example.jpabook.entity.order.OrderStatus;
import com.example.jpabook.repository.OrderRepository;
import com.example.jpabook.repository.OrderSearch;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<OrderDTO> orderV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(Collectors.toList());
    }

    @Data
    static class OrderDTO {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDTO> orderItems;

        public OrderDTO(Order order) {
            orderId = order.getId();
            name = order.getMember().getUserName(); // Lazy 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // Lazy 초기화
            orderItems = order.getOrderItems().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toList());
        }
    }
    @Getter
    static class OrderItemDTO {
        private String itemName;
        private int orderPrice;
        private  int count;

        public OrderItemDTO(OrderItem orderItem){
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDTO> orderV2() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDTO> orderV3(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(Collectors.toList());
    }
}
