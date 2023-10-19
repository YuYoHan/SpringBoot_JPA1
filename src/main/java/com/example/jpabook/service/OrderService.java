package com.example.jpabook.service;

import com.example.jpabook.entity.item.Item;
import com.example.jpabook.entity.member.Member;
import com.example.jpabook.entity.order.Delivery;
import com.example.jpabook.entity.order.Order;
import com.example.jpabook.entity.order.OrderItem;
import com.example.jpabook.repository.ItemRepository;
import com.example.jpabook.repository.MemberRepository;
import com.example.jpabook.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }


    // 취소

    // 검색
}
