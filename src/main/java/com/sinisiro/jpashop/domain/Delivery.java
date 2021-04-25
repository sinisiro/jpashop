package com.sinisiro.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private HomeAddress address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
