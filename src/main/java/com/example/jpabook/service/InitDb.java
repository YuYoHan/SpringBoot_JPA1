package com.example.jpabook.service;

import com.example.jpabook.entity.item.Book;
import com.example.jpabook.entity.member.Address;
import com.example.jpabook.entity.member.Member;
import com.example.jpabook.entity.order.Delivery;
import com.example.jpabook.entity.order.Order;
import com.example.jpabook.entity.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void doInit1() {
            Member member = Member.builder()
                    .userName("userA")
                    .address(new Address("서울", "1", "111"))
                    .build();
            em.persist(member);

            Book book = Book.builder()
                    .name("JPA Book")
                    .price(10000)
                    .stockQuantity(100)
                    .build();
            em.persist(book);

            Book book2 = Book.builder()
                    .name("JPA Book2")
                    .price(20000)
                    .stockQuantity(100)
                    .build();
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = Delivery.builder()
                    .address(member.getAddress())
                    .build();

            // Order을 보면 만드는 메소드에 매개변수 중 하나가
            // OrderItem... 이렇게 되어 있는데
            // 이렇게 있으면 전부 넘겨받을 수 있다.
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }

}
