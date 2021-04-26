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

    //JPA 스펙상 엔티티나 임베디드 타입( @Embeddable )은 자바 기본 생성자(default constructor)를
    //public 또는 protected 로 설정
    //사용자가 파라미터가 필요한 생성자를 재정의 하는 경우에는 반드시 파라미터가 없는 기본 생성자를 제공해 주어야 한다
    //https://goodyhlee.wordpress.com/2016/06/20/177/
    protected HomeAddress(){

    }

    public HomeAddress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
