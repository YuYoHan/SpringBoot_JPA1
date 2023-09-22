package com.example.jpabook.entity.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@ToString
@Getter
@DiscriminatorValue("Album")
public class Album extends Item{
    private String artist;
    private String etc;

    @Builder
    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}
