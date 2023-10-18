package com.example.jpabook.entity.order;

import com.example.jpabook.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Long id,
                 Member member,
                 List<OrderItem> orderItems,
                 Delivery delivery,
                 LocalDateTime orderDate,
                 OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;

        if(member != null) {
            member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 생성 메서드
    public static Order createOrder(Member member,
                                    Delivery delivery,
                                    OrderItem... orderItems2) {
        ArrayList<OrderItem> arrayList = new ArrayList<>();
        for(OrderItem orderItem : orderItems2) {
            arrayList.add(orderItem);
        }
        return Order.builder()
                .member(member)
                .delivery(delivery)
                .orderItems(arrayList)
                .status(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();
    }

    // 비즈니스 로직
    // 주문 취소
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP) {
           throw  new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // 조회로직
    // 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
