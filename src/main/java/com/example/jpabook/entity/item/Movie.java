package com.example.jpabook.entity.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue("Movie")
public class Movie extends Item{
    private String director;
    private String isbn;

    @Builder
    public Movie(String director, String isbn) {
        this.director = director;
        this.isbn = isbn;
    }
}
