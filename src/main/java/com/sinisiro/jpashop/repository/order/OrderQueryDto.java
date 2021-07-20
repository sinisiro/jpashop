package com.sinisiro.jpashop.repository.order;

import com.sinisiro.jpashop.domain.HomeAddress;
import com.sinisiro.jpashop.domain.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private HomeAddress address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate,
                         OrderStatus orderStatus, HomeAddress address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
