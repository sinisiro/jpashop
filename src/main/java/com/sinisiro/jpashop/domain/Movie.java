package com.sinisiro.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;

    @Builder
    public Movie(Long id, String name, int price, int stockQuantity, String director, String actor, List<Category> categories) {
        super(id,name,price,stockQuantity,categories); // 부모 클래스 생성자 호출
        this.director = director; // 추가된 필드
        this.actor = actor; // 추가된 필드
    }
}
