package com.sinisiro.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;


@Getter @Setter
@Embeddable
public class HomeAddress {

    private String city;
    private String street;
    private String zipcode;
}
