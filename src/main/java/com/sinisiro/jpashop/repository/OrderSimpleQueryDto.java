package com.sinisiro.jpashop.repository;

import com.sinisiro.jpashop.domain.HomeAddress;
import com.sinisiro.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderdate; //주문시간
    private OrderStatus orderStatus;
    private HomeAddress address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, HomeAddress address) {
        this.orderId = orderId;
        this.name = name;
        this.orderdate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

}
