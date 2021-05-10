package com.sinisiro.jpashop.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("B")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Item{

    private String author;
    private String isbn;



    @Builder
    public Book(Long id, String name, int price, int stockQuantity, String author, String isbn, List<Category> categories) {
        super(id,name,price,stockQuantity,categories); // 부모 클래스 생성자 호출
        this.author = author; // 추가된 필드
        this.isbn = isbn; // 추가된 필드
    }
}
