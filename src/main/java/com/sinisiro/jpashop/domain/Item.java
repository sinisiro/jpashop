package com.sinisiro.jpashop.domain;


import com.sinisiro.jpashop.exception.NotEnoughStockException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //??
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==/

    /**
     * 재고 증가
     * @param quantity
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     *
     * @param quantity
     */
    public void removeStock(int quantity){
       int restStock = this.stockQuantity - quantity;
       if(restStock<0) {
           throw new NotEnoughStockException("need more stock");
       }
       this.stockQuantity = restStock;
       }
    }

