package com.sinisiro.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderdate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDeliver(Delivery deliver){
        this.delivery = deliver;
        deliver.setOrder(this);
    }

    public static Order createOrder(Member member,Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDeliver(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderdate(LocalDateTime.now());
        return order;
    }

    /**
     * 주문취소
     */
    public void cancle(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
          throw new IllegalStateException("can't cancel");
        }

        this.setStatus(OrderStatus.CANCEL);
    }

    public int getTotalPrice(){
        int totalPrice =0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

}
