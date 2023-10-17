package com.example.jpabook.repository;

import com.example.jpabook.entity.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        // 상품을 처음 등록할 때는 id가 없으니
        // id가 없으면 신규 상품 등록
        if(item.getId() == null) {
            em.persist(item);
        } else {
            // id가 있으면 DB에 있으니
            // 업데이트
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
