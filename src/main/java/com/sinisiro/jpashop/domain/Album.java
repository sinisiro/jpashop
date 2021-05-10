package com.sinisiro.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;
    @Builder
    public Album(Long id, String name, int price, int stockQuantity, String artist, String etc, List<Category> categories) {
        super(id,name,price,stockQuantity,categories); // 부모 클래스 생성자 호출
        this.artist = artist; // 추가된 필드
        this.etc = etc; // 추가된 필드
    }
}
