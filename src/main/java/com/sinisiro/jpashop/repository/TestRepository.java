package com.sinisiro.jpashop.repository;

import com.sinisiro.jpashop.domain.Item;
import com.sinisiro.jpashop.domain.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public Test findOne(Long id){
        return em.find(Test.class,id);
    }

    public void delete(Test item){
        em.remove(item);
    }
}
