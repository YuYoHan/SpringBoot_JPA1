package com.example.jpabook.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;

    @Builder
    public Member(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
