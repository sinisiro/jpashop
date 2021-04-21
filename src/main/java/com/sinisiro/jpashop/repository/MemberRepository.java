//package com.sinisiro.jpashop.repository;
//
//import com.sinisiro.jpashop.domain.Member;
//import com.sinisiro.jpashop.domain.testMember;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Repository
//public class MemberRepository {
//    @PersistenceContext
//    EntityManager em;
//    public Long save(testMember member) { em.persist(member);
//        return member.getId();
//    }
//    public testMember find(Long id) {
//        return em.find(testMember.class, id);
//    }
//}
//
