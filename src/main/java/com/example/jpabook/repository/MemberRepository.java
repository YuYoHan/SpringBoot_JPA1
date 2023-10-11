package com.example.jpabook.repository;

import com.example.jpabook.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return  em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // Member에 대한 엔티티 객체에 대해서
        return em.createQuery("select m from  Member  m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return  em.createQuery("select  m from Member m where m.userName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
