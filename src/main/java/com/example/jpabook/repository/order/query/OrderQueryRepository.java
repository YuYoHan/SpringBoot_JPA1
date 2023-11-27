package com.example.jpabook.repository.order.query;

import com.example.jpabook.domain.OrderQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDTO> findOrderQueryDTOs() {
        em.createQuery(
                "select new com.example.jpabook.domain.OrderQueryDTO(o.id, m.name, o.orderDate, o.status) from Order o" +
                        " join o.meber m" +
                        " join o.delivery d", OrderQueryDTO.class
        ).getResultList();
    }
}
