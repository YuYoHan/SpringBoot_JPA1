package com.example.jpabook.entity.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Getter
@DiscriminatorValue("Book")
public class Book extends Item{
    private String author;
    private String isbn;

    @Builder
    public Book(String author,
                String isbn,
                Long id,
                String name,
                int price,
                int stockQuantity,
                List<Catagory> catagories) {
        super(id, name, price, stockQuantity, catagories);
        this.author = author;
        this.isbn = isbn;
    }
}