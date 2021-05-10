package com.sinisiro.jpashop.repository;

import com.sinisiro.jpashop.domain.Book;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public Book findOne(Long id){
        return em.find(Book.class,id);
    }
}
