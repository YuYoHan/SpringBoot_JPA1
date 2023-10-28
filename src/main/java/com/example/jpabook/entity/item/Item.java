package com.example.jpabook.entity.item;

import com.example.jpabook.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Catagory> catagories = new ArrayList<>();

    @Builder
    public Item(Long id, String name, int price, int stockQuantity, List<Catagory> catagories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.catagories = catagories;
    }


    // 비즈니스 로직
    // stock 증가
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    // stcck 감소
    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
