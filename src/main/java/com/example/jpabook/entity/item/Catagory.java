package com.example.jpabook.entity.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Catagory {
    @Id @GeneratedValue
    @Column(name = "category")
    private Long id;

    private String name;

    // 실무에서는 사용하지 않음
    @ManyToMany
    @JoinTable(name = "category_item",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Catagory parent;

    @OneToMany(mappedBy = "parent")
    private List<Catagory> child = new ArrayList<>();

    @Builder
    public Catagory(Long id, String name, List<Item> items, Catagory parent, List<Catagory> child) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.parent = parent;
        this.child = child;

        // 연관관계 메소드
        if(child != null) {
            child.add(this);
        }
    }
}
