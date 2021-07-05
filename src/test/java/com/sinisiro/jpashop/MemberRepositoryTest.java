package com.sinisiro.jpashop;

import com.sinisiro.jpashop.domain.Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class MemberRepositoryTest {

    @PersistenceContext
    EntityManager em;
//    MemberRepository memberRepository;

    @org.junit.Test
    @Transactional
    @Rollback(false)
    public void testMember() {

        Test test = new Test();
        test.setId(3L);

        em.remove(test);

//        Member member = new Member();
//
//        member
//        member.setName("123");
//        Long savedId = memberRepository.save(member);
//
//        member = memberRepository.find(savedId);
//        assertEquals(member.getUsername(), memberRepository.find(savedId).getUsername());
    }
}

