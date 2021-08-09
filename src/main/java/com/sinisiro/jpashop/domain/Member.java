package com.sinisiro.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;

//    public Member() {
//    }
//
//    public Member(String name, HomeAddress address) {
//        this.name = name;
//        this.address = address;
//    }

    @Embedded
    private HomeAddress address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
