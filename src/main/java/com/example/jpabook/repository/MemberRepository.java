package com.example.jpabook.repository;

import com.example.jpabook.entity.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Scanner;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

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

    public List<Member> findByName(Scanner name) {
        return  em.createQuery("select  m from Member m where m.userName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
