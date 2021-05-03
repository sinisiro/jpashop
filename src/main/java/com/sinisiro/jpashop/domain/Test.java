package com.sinisiro.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "testEn")
@Entity
@Getter @Setter @ToString
public class Test {

    @Id
    @GeneratedValue
    @Column(name="test_id")
    private Long id;
    private String name;


}
