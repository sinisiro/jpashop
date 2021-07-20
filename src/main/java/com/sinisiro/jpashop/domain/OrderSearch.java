package com.sinisiro.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
