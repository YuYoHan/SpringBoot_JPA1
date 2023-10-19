package com.example.jpabook.entity.order;

import com.example.jpabook.entity.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;     // 주문 가격
    private int count;          // 주문 수량


    // 생성 메소드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 주문한 만큼 상품 재고를 까줘야 한다.
        item.removeStock(count);
        return orderItem;
    }


    /* 비즈니스 로직 */
    public void cancel() {
        getItem().addStock(count);
    }

    /* 조회 로직*/
    // 주문할 때 주문 가격과 수량을 곱해야하기 때문에
    // 여기서 가져온다.
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}