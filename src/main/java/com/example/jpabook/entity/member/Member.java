package com.example.jpabook.entity.member;

import com.example.jpabook.entity.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String userName, Address address, List<Order> orders) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.orders = orders;
    }
}
